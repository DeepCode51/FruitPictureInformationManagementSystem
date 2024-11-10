<style type="text/scss" lang="scss">
    .imgError{
        background: url("./images/timeout2.png") no-repeat;
        background-size: cover;
    }
</style>
<script>
    import {formatImageSrc} from "@/utils/utils";

    export default {
        name: "e-img",
        data() {
            return {
                imgError:false
            }
        },
        props:{
            src:String,
            alt:String,
            title:String
        },
        methods:{
            error(){
                this.$el.src = './static/images/not.gif';
                this.imgError=true
            }
        },
        render(createElement) {
            if(this.src == '')
            {
                return null;
            }else{
                var src = this.src.split(',');

                return createElement('img' , {
                    class:{
                        imgError:this.imgError
                    },
                    attrs:{
                        src: formatImageSrc(src[0]),
                        alt:this.alt,
                        title:this.title
                    },
                    on:{
                        error:this.error,
                        load:()=>{
                            this.imgError = false;
                        },
                        ...this.$listeners
                    }
                });
            }
        },
    }
</script>
