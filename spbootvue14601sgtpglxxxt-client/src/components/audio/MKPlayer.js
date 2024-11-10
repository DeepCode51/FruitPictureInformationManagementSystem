(function () {
    var $MKPlayerTpl,MKAudio,MKVM , MKPlayer;
    var currentProgress = null;
    var currentPostion = {
        x:0,
        y:0
    };

    function updateProgress(e)
    {
        var offset = currentProgress.offset;
        var pageX = e.pageX;
        var  posX  =  pageX;
        if(posX > offset.left + offset.width){
            posX = offset.left + offset.width;
        }
        if(posX < offset.left)
        {
            posX = offset.left;
        }
        posX -= offset.left;
        // 求百分比
        var val = (posX / offset.width) * 100;
        currentProgress.$emit('input' , val);
        currentProgress.$emit('changed' , val);
    }

    $(document).on('mousemove' , function (e) {
        if(currentProgress == null)return;
        updateProgress(e);
    });
    $(document).on('mouseup' , function (e) {
        if(currentProgress == null)return;
        updateProgress(e);
        currentProgress = null;
    });
    var progress = Vue.extend({
        data:function (e) {
            return {
                offset:null,
                isMove:false
            };
        },
        methods:{
            mousedown:function (e) {
                // 进度条锁住了，不允许拖动
                if(this.lock)return;
                currentProgress = this;
                this.offset = $(this.$el).offset();
                this.offset.width = $(this.$el).width();

                currentPostion.x = e.pageX;
                currentPostion.y = e.pageY;
            },
        },
        props:{
            value:[Number],
            lock:[Boolean]
        },
        template:'<div class="MKPlayer-progress-box" @mousedown="mousedown">'+
            '<span class="MKPlayer-progress-slot"></span>'+
            '<span class="MKPlayer-progress-progress" :style="{width:value+\'%\'}"></span>'+
            '<span class="MKPlayer-progress-postion" :style="{left:value+\'%\'}"></span>'+
            '</div>'
    });

    Vue.component('mk-progress' , progress);
    function initHTML() {
        // 写入HTML
        //var MKHtml = '<div cl';
        $MKPlayerTpl = $('.MKPlayer');
        MKAudio = document.createElement('audio');
        document.body.appendChild(MKAudio);

        var timer = null;
        MKVM = new Vue({
            el:'#MKPlayer',
            data:MKPlayer.data,
            watch:{
                message:function (val) {
                    if(val!='')
                    {
                        var that = this;
                        clearTimeout(timer);
                        timer = setTimeout(function () {
                            that.message = '';
                        },5000);
                    }
                },
                volume:{
                    handler:function (val) {
                        MKAudio.volume = MKPlayer.data.volume / 100;
                    },
                    immediate:true
                },
                scrollTop:function (val) {
                    var mklyric = $(this.$el).find('.mklyric');
                    //console.log(val);
                    //mklyric.stop().animate({scrollTop: val}, 300);
                    this.scrollTopText = 'translate(0px,'+(val*-1)+'px)'
                }
            },
            methods:{
                // 收藏被点击
                collect:function (music) {
                    MKPlayer.onCollect(music);
                },
                /**
                 * 下载被点击
                 * @param music
                 */
                download:function (music) {
                    MKPlayer.onDownload(music);
                },
                /**
                 * 喜欢被点击
                 * @param music
                 */
                like:function (music) {
                    MKPlayer.onLike(music);
                },
                /**
                 * 上一首被点击
                 */
                prevMusic:function () {
                    MKPlayer.prev();
                },
                /**
                 * 播放被点击
                 */
                playMusic:function () {
                    MKPlayer.play();
                },
                /**
                 * 暂停被点击
                 */
                pauseMusic:function () {
                    MKPlayer.pause();
                },
                setMute:function(){
                    MKPlayer.setMute(!this.mkmute);
                },
                /**
                 * 下一首被点击
                 */
                nextMusic:function () {
                    MKPlayer.next();
                },
                /**
                 * 播放顺序被点击
                 */
                updateOrderby:function () {
                    this.playOrderby++;
                    if(this.playOrderby >= this.orderby.length)
                    {
                        this.playOrderby=0;
                    }
                    switch (this.playOrderby) {
                        case 0:
                            this.message = '列表循环播放';
                            break;
                        case 1:
                            this.message = '随机播放';
                            break;
                        case 2:
                            this.message = '单曲循环';
                            break;
                    }
                },
                playIndex:function(index)
                {
                    MKPlayer.playIndex(index);
                },
                /**
                 * 歌单被点击
                 */
                gedanClick:function () {
                    MKVM.openMusicList = !MKVM.openMusicList;
                },

                /**
                 * 设置进度
                 */
                onMusicProsess:function (prosess) {
                    var duration = MKAudio.duration;
                    if ( duration > 0 ) {
                        MKAudio.currentTime = prosess/100 * duration;
                    }
                },
                onVolumeChange: function (pros) {
                    this.message = '调节音量：' + Math.floor(pros);
                }
            }
        });
    }
    $(document).on('click' , function () {
        if(MKVM.openMusicList){MKVM.openMusicList = false;}
    });
    function cache(name , value) {
        if(!window.localStorage)return null;
        if(value === undefined){
            var v = localStorage.getItem(name);
            if(v){
                return JSON.parse(v);
            }
        }else{
            localStorage.setItem(name,JSON.stringify(value));
        }
        return null;
    }


    var musicListHis = cache('musicList');
    if(musicListHis == null){
        musicListHis = [];
    }

    var MKPLAYINIT = false;
    MKPlayer = {
        data:{
            message:'',
            musicList:musicListHis,
            lyricList:{},
            music:{},  // 当前播放音乐
            musicIndex:0,
            volume:50, // 音量
            musicProsess:0, // 播放器进度
            orderby:['mklist','mkrandom','mksingle'],
            playOrderby:0,
            playState:false,
            //lockProsess:true,
            openMusicList:false,
            progressTime:'00:00/00:00',
            // 歌词滚动
            scrollTop:0,
            scrollTopText:'',
            lastLyric:-1,
            mkmute:false
        },
        init:function () {
            if(MKPLAYINIT)return;
            initHTML();
            initEvent();
        },
        onCollect:function (music) {

        },
        setMute:function(isMute){
            MKVM.mkmute = isMute;
            MKAudio.muted = isMute;
        },
        onLike: function(music){

        },
        onGetMusicUrl: function(music , callback){
            callback(music.url);
        },
        onDownload: function(music){
            MKPlayer.onGetMusicUrl(music , function ( url ) {
                openDownloadDialog(url , music.name +'-'+ music.artist)
            })
        },
        // 下一首
        next:function (){
            switch (MKVM.playOrderby) {
                case 0:
                case 2: // 单曲循环，不需要改
                    // 顺序播放
                    MKVM.musicIndex++;
                    if(MKVM.musicIndex>= MKVM.musicList.length)
                    {
                        MKVM.musicIndex = 0;
                    }
                    MKVM.music = MKVM.musicList[MKVM.musicIndex];
                    break;
                case 1:  // 随机循环
                    MKVM.musicIndex = randomFrom(0 , MKVM.musicList.length-1);//Math.floor(Math.random() * )
                    MKVM.music = MKVM.musicList[MKVM.musicIndex];
                    break;
            }
            MKVM.playState = false;
            MKPlayer.reset();
            MKPlayer.loadUrl(function () {
                MKPlayer.play();
            });
        },
        prev: function(){
            switch (MKVM.playOrderby) {
                case 0:
                case 2: // 单曲循环，不需要改
                    // 顺序播放
                    MKVM.musicIndex--;
                    if(MKVM.musicIndex < 0)
                    {
                        MKVM.musicIndex = 0;
                    }
                    MKVM.music = MKVM.musicList[MKVM.musicIndex];
                    break;
                case 1:  // 随机循环
                    MKVM.musicIndex = randomFrom(0 , MKVM.musicList.length-1);//Math.floor(Math.random() * )
                    MKVM.music = MKVM.musicList[MKVM.musicIndex];
                    break;
            }

            MKVM.playState = false;
            MKPlayer.reset();
            MKPlayer.loadUrl(function () {
                MKPlayer.play();
            });
        },
        play: function(){
            if(MKVM.playState){
                return;
            }
            if(!MKVM.music.name){
                MKPlayer.reset();
                MKPlayer.loadUrl(function () {
                    MKPlayer.play();
                });
            }else{
                MKAudio.play();
            }
            MKVM.playState = true;
        },
        reset:function(){
            MKVM.music = MKVM.musicList[MKVM.musicIndex];
            MKAudio.currentTime = 0;
            MKVM.musicProsess = 0;  // 重置
            MKPlayer.loadLyric();
        },
        loadUrl:function(callback){
            MKPlayer.onGetMusicUrl(MKVM.music , function ( url ) {
                MKAudio.src = url;
                MKAudio.play();
                MKPlayer.loadLyric();
                callback();
            });
        },
        loadLyric:function(){
            // 加载歌词
            MKVM.scrollTop = 0;
            MKVM.lastLyric = -1;
            var music = MKVM.music;
            if ( music.lyric ) {
                MKVM.lyricList = parseLyric(music.lyric);
                MKVM.$nextTick(function () {
                    MKPlayer.refreshLyric(0);
                })
            }
        },
        parseLyric:function(lyric){
            return parseLyric(lyric);
        },
        refreshLyric: function(time){
            // 强制刷新歌词
            if(MKVM.lyricList.length == 0) return false;

            time = parseInt(time);  // 时间取整
            var i = 0;
            for(var k in MKVM.lyricList){
                if(k >= time) break;
                i = k;      // 记录上一句的
            }
            MKPlayer.scrollLyric(i);
        },
        scrollLyric: function(time){
            if(!MKVM.lyricList[time]){
                return;
            }

            if(MKVM.lastLyric == time) return true;
            MKVM.lastLyric = time;
            var i = 0;  // 获取当前歌词是在第几行
            for(var k in MKVM.lyricList){
                if(k == time) break;
                i++;
            }
            var mklyric = $(MKVM.$el).find('.mklyric');
            var scroll = (mklyric.find('li').height() * i) - ($(MKVM.$el).find('.MKPlayer-play-disc').height() / 2);
            MKVM.scrollTop = scroll;
        },
        pause: function(){
            if(!MKVM.playState){
                return;
            }
            MKAudio.pause();
        },
        // 播放错误
        error:function (){
            MKVM.playState = false;
        },
        // 清除播放列表
        clear:function () {
            MKVM.musicList = [];
        },
        deleteMusicIndex:function(i){
            var music = MKVM.musicList[i];
            if(music == MKVM.music){
                MKVM.music = {};
            }
            MKVM.musicList.splice(i,1);
        },
        deleteMusic:function(music){
            for(var i=0;i<MKVM.musicList.length;i<0)
            {
                if(music == MKVM.musicList[i]){
                    MKPlayer.deleteMusicIndex(i);
                    break;
                }
            }
        },
        addMusic:function(music){
            for(var i=0;i<MKVM.musicList.length;i++){
                if(music.id == MKVM.musicList[i].id){
                    return i;
                }
            }
            MKVM.musicList.push(music);
            clearTimeout(MKPlayer.timer);
            MKPlayer.timer = setTimeout(function () {
                cache('musicList' , MKVM.musicList);
            },500);
            return MKVM.musicList.length-1;
        },
        playIndex:function(index){
            if(index < 0){
                index = 0;
            }
            if(index >= MKVM.musicList.length){
                index = MKVM.musicList.length-1;
            }
            MKVM.musicIndex = index;
            MKPlayer.reset();
            MKPlayer.loadUrl(function () {
                MKPlayer.play();
            });
        },
        addMusicArray:function(musicArray){
            for(var i=0;i<musicArray.length;i++)
            {
                MKPlayer.addMusic(musicArray[i]);
            }
        },
        clearAddMusicArray:function (musicArray) {
            MKPlayer.clear();
            MKPlayer.addMusicArray(musicArray);
        }
    };




    function initEvent() {
        // 更新进度
        $(MKAudio).on('timeupdate' , function (e) {
            MKVM.musicProsess = MKAudio.currentTime / MKAudio.duration * 100;
            MKVM.progressTime = formatTime(MKAudio.currentTime)+'/'+formatTime(MKAudio.duration);
            MKPlayer.refreshLyric(MKAudio.currentTime);
        });
        // 开始播放了
        $(MKAudio).on('play' , function (e) {
            MKVM.playState = true;
        });
        // 暂停
        $(MKAudio).on('pause' , function () {
            MKVM.playState = false;
        });
        $(MKAudio).on('ended' , function () {
            switch (MKVM.playOrderby) {
                case 0:
                    // 顺序播放
                    MKVM.musicIndex++;
                    if(MKVM.musicIndex>= MKVM.musicList.length)
                    {
                        MKVM.musicIndex = 0;
                    }
                    MKVM.music = MKVM.musicList[MKVM.musicIndex];
                    break;
                case 1:  // 随机循环
                    MKVM.musicIndex = randomFrom(0 , MKVM.musicList.length-1);//Math.floor(Math.random() * )
                    MKVM.music = MKVM.musicList[MKVM.musicIndex];
                    break;
                case 2: // 单曲循环，不需要改
                    break;
            }
            MKVM.playState = false;
            MKPlayer.reset();
            MKPlayer.loadUrl(function () {
                MKPlayer.play();
            });
        } ); // 播放结束
        $(MKAudio).on('error' , function (e) {

            MKVM.message = MKVM.music.name+'播放出错';
            MKPlayer.deleteMusicIndex(MKVM.musicIndex);
            MKPlayer.error(e);
            MKPlayer.reset();
            MKPlayer.loadUrl(function () {
                MKPlayer.play();
            });

        }); // 播放结束
    }


    /**
     * 通用的打开下载对话框方法，没有测试过具体兼容性
     * @param url 下载地址，也可以是一个blob对象，必选
     * @param saveName 保存文件名，可选
     * http://www.cnblogs.com/liuxianan/p/js-download.html
     */
    function openDownloadDialog(url, saveName) {
        if (typeof url == 'object' && url instanceof Blob) {
            url = URL.createObjectURL(url); // 创建blob地址
        }
        var aLink = document.createElement('a');
        aLink.href = url;
        aLink.target = "_blank";
        aLink.download = saveName || ''; // HTML5新增的属性，指定保存文件名，可以不要后缀，注意，file:///模式下不会生效
        var event;
        if (window.MouseEvent) event = new MouseEvent('click');
        else {
            event = document.createEvent('MouseEvents');
            event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
        }
        aLink.dispatchEvent(event);
    }

    function randomFrom(lowerValue,upperValue)
    {
        return Math.floor(Math.random() * (upperValue - lowerValue + 1) + lowerValue);
    }

    // 将时间格式化为 00:00 的格式
// 参数：原始时间
    function formatTime(time) {
        var hour, minute, second;
        hour = (parseInt(time / 3600, 10));
        if (hour <10) hour = '0' + hour;

        minute = (parseInt((time % 3600) / 60, 10));
        if (minute < 10) minute = '0' + minute;

        second = (parseInt(time % 60, 10));
        if (second < 10) second = '0' + second;

        if (hour > 0) {
            return hour + ":" + minute + ":" + second;
        } else {
            return minute + ":" + second;
        }
    }

    // 解析歌词
    // 这一函数来自 https://github.com/TivonJJ/html5-music-player
    // 参数：原始歌词文件
    function parseLyric(lrc) {
        if(lrc === '') return '';
        var lyrics = lrc.split("\n");
        var lrcObj = {};
        for(var i=0;i<lyrics.length;i++){
            var lyric = decodeURIComponent(lyrics[i]);
            var timeReg = /\[\d*:\d*((\.|\:)\d*)*\]/g;
            var timeRegExpArr = lyric.match(timeReg);
            if(!timeRegExpArr)continue;
            var clause = lyric.replace(timeReg,'');
            for(var k = 0,h = timeRegExpArr.length;k < h;k++) {
                var t = timeRegExpArr[k];
                var min = Number(String(t.match(/\[\d*/i)).slice(1)),
                    sec = Number(String(t.match(/\:\d*/i)).slice(1));
                var time = min * 60 + sec;
                lrcObj[time] = clause;
            }
        }
        return lrcObj;
    }



    window.MKPlayer = MKPlayer;
})();

