<template>
    <div class="detail" v-if="address.lng">
        <baidu-map class="map" :center="{lng: address.lng, lat: address.lat}" :zoom="15">
            <bm-info-window :position="{lng: address.lng, lat: address.lat}"
                            title="位置信息"
                            :show="infoWindow"
                            @close="infoWindowClose" @open="infoWindowOpen">
                <p v-text="address.address"></p>
                <el-button type="primary" @click="open">导航</el-button>
            </bm-info-window>
            <bm-marker :position="{lng:address.lng,lat:address.lat}" :dragging="false" animation="BMAP_ANIMATION_BOUNCE"></bm-marker>
        </baidu-map>
        <a :href="daohangUrl" ref="daohang" target="_blank"></a>
    </div>
</template>
<style type="text/scss" scoped lang="scss">
.detail {
    width: 100%;
    height: 350px;
    .map {
        width: 100%;
        height: 100%;
        img{
            max-width: none;
            max-height: none;
        }
    }
}


</style>
<script>
import {isObject} from "@/utils/extend";
export default {
    name: "e-baidu-address-detail",
    data() {
        return {
            daohangUrl:'',
            infoWindow: true
        }
    },
    props:{
        map:[Object,String]
    },
    watch: {},
    computed: {
        address(){
            var map = this.map;
            console.log(map)
            if(!map){
                return {};
            }
            if(!isObject(map)){
                map = JSON.parse(map);
            }
            return map;
        }
    },
    methods: {
        infoWindowClose () {
            this.infoWindow = false
        },
        infoWindowOpen () {
            this.infoWindow = true
        },
        open(){
            var obj  = this.address;
            var url  = "http://api.map.baidu.com/marker?location="+obj.lat+","+obj.lng+"&title=目的地&content="+obj.address+"&output=html&src=webapp.baidu.openAPIdemo&output=html";
            var a    = document.createElement('a');
            a.href   = url;
            a.target = '_blank';
            a.click();
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
