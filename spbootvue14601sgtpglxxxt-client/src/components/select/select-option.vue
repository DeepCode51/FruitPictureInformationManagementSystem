<script>
import {isFunction} from "@/utils/extend";
import api from "@/api";

export default {
    name: "e-select-option",
    data() {
        return {
            options: []
        }
    },
    props: {
        type: {
            type: String,
            default: 'radio'
        },
        module: {
            type: String,
            required: true
        },
        value: {
            type: String,
            required: true
        },
        label: {
            type: String,
            required: true
        },
        filter:{
            type:Function,
            default:null
        }
    },
    watch: {},
    created() {
        var where = {};

        this.$post(api.search.select , {
            table:this.module,
            where:JSON.stringify(where)
        }).then(lists=>{

            if(this.filter && isFunction(this.filter)){
                lists = this.filter(lists);

            }
            this.options = lists;

        }).catch(err=>{
        });
    },
    render(createElement) {
        var {type,  value, label, options} = this;
        var result = [];
        var ci;
        switch (type) {
            case 'radio':
            case 'checkbox':
                for (ci of options) {
                    result.push(createElement('el-' + type, {
                        props: {
                            label: ''+(ci[value]),
                        },
                    }, [ci[label]]));
                }
                break;
            default:
                for (ci of options) {
                    result.push(createElement('el-' + type, {
                        props: {
                            value: ci[value],
                            label: ci[label],
                        },
                        on:this.$listeners,
                        attrs:this.$attrs,
                    }));
                }
                break;
        }
        return createElement('div' , {},result);
    },
    computed: {},
}
</script>
