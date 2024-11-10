<template>
    <div class="">

        <baidu-map :center="{lng:data.lng,lat:data.lat}" @ready="readyBaidu" :zoom="data.zoom" class="baidumap" style="height: 250px" ref="baiduMap">
            <bm-view class="map" style=";height: 250px"></bm-view>
            <bm-control :offset="{width: '10px', height: '10px'}" v-if="initMap">
                <el-input v-model="address" style="width: 250px" placeholder="请输入地名" />
            </bm-control>

            <bm-local-search v-if="initMap" class="localSearch" style="width: 350px;height: 250px;overflow: hidden auto" :auto-viewport="true" :keyword.sync="address"
                             @infohtmlset="searchComplete"></bm-local-search>
        </baidu-map>
        {{value}}
    </div>

</template>
<style type="text/scss" lang="scss">
    .baidumap{
        display: flex;
        .localSearch{
            padding: 0 10px;
        }
    }
    .map{
        flex-grow: 1;
        img{
            max-width: none;
            max-height: none;
        }
    }
</style>
<script>
    import jdk from '@/utils/extend'
    export default {
        name: "e-baidu-address",
        data() {
            return {
                initMap:false,
                address:''
            }
        },
        props:{
            value:[String,Object],
        },
        watch: {},
        computed: {
            data(){
                var result = this.value;
                try{
                    if( !jdk.isObject(result) )
                    {
                        result = JSON.parse(result);
                    }
                }catch (e) {
                    result = {
                        address:'',
                        lng:114.053703,
                        lat:22.673854,
                        zoom:15
                    };
                }
                return result;
            }
        },
        methods: {
            readyBaidu({BMap, map}){
                this.initMap = true;
                this.$BMap = BMap;
                this.address = this.data.address;
            },

            searchComplete(e){
                this.showPointValue(e)
            },
            showPointValue(p)
            {
                var $this = this;
                var bmap = $this.$refs.baiduMap.map;
                var point = p.point;

                var obj = {
                    lng:point.lng,
                    lat:point.lat,
                    zoom:bmap.getZoom(),
                    title:p.title,
                    address:p.address || this.address
                };
                this.$emit('input' , JSON.stringify(obj));
            }
        },
        created() {

        },
        mounted() {

        },
        destroyed() {
        }
    }
</script>
