<template>
  <el-checkbox-group v-model="select" @change="changeSelect">
    <el-checkbox v-for="v in list" :label="v"> {{ v.zimu }} 、 {{ v.title }} </el-checkbox>
  </el-checkbox-group>
</template>

<script>

import {findObject} from "@/utils/utils";
import {each} from "@/utils/extend";

export default {
  name: "t-checkbox",
  data () {
    return {
      select:[],
    }
  },
  props:{
    obj:Object,
    paper:String,
    daan:String
  },
  computed:{
    list(){
      if(!this.paper){
        return [];
      }
      return eval("("+this.paper+")")
    }
  },
  methods:{
    changeSelect(val){
      var daan = [];
      var zimu = [];
      var defen = 0;
      each(this.select , (i,o)=>{
        daan.push(o.title);
        zimu.push(o.zimu);
        defen +=Math.floor( o.point === undefined ?  o.defen : o.point);
      });

      this.obj.zimu = zimu.join(","),
          this.obj[this.daan] = daan.join(","),
          this.obj.defen = defen;
    }
  }
}
</script>

<style scoped>

</style>