<template>
    <div class="MKPlayer-progress-box" @mousedown="mousedown">
        <span class="MKPlayer-progress-slot"></span>
        <span class="MKPlayer-progress-progress" :style="{width:value+'%'}"></span>
        <span class="MKPlayer-progress-postion" :style="{left:value+'%'}"></span>
    </div>
</template>
<style type="text/scss" lang="scss">

</style>
<script>

    var {addEventListener} = require('add-dom-event-listener')
    export default {
        name: "progress",
        data:function () {
            return {
                offset:{
                    x:0,
                    y:0,
                    width:0
                },
                isMove:false,
            };
        },
        props:{
            value:[Number],
            lock:[Boolean]
        },
        methods:{
            offsetLeft (element) {
                var actualLeft = element.offsetLeft;
                var current = element.offsetParent;
                while (current) {
                    actualLeft += current.offsetLeft;
                    current = current.offsetParent;
                }
                return actualLeft;
            },
            mousedown (e) {
                // 进度条锁住了，不允许拖动
                if(this.lock)return;
                this.isMove = true;

                this.offset.x = e.pageX;
                this.offset.y = e.pageY;
                this.offset.width = this.$el.offsetWidth;
                this.offset.left = this.offsetLeft(this.$el)
            },
            mousemove(e){
                var offset = this.offset;
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
                var val = ((posX / offset.width) * 100).toFixed(2);
                this.$emit('input' , val)
                this.$emit('changed' , val)
            },

        },
        mounted() {
            this.handlerMove = addEventListener(document.body , 'mousemove' , (e)=>{
                if(!this.isMove){return;}
                this.mousemove(e);
            });
            this.handlerUp = addEventListener(document.body , 'mouseup' , (e)=>{
                if(!this.isMove){return;}
                this.mousemove(e);
                this.isMove = false;
            });
        },
        beforeDestroy() {
            this.handlerMove.remove();
            this.handlerUp.remove();
        }

    }
</script>
