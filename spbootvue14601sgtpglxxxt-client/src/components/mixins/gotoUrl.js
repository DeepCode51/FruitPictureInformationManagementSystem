import {formatImageSrc} from "@/utils/utils";

export default {
    props:{
        to:[String,Object],
        replace:{
            type:Boolean,
            default:false,
        },
    },
    methods:{
        $gotoUrl(){
            if(this.to){
                var url = this.to;
                if( url.match(/^https?:\/\//ig) ){
                    if(this.replace){
                        location.replace( url );
                    }else{
                        location.href = url;
                    }
                    return;
                }
                if(this.replace){
                    this.$router.replace(this.to);
                }else{
                    this.$router.push(this.to);
                }
            }
        },
        $getImage( image ){
            return image ? formatImageSrc(image.split(',')[0]) : '';
        }
    },

}
