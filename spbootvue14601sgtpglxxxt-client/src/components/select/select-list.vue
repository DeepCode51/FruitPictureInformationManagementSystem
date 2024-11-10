<template>
    <div class="e-select-list">
        <div class="select-update">
            <div>
                <el-select @change="$emit('input' , $event)" :value="value" filterable>
                    <el-option :value="0" label="请选择"></el-option>
                    <el-option :value="v.id" :label="getLabel(v)" v-for="v in list" :key="v.id"></el-option>
                </el-select>
            </div>
            <div>
                关键词：
                <el-input style="width: 150px" v-model="searchMsg.keyword" />
            </div>
            <div>
                <slot name="search"></slot>
            </div>
            <!--<div class="search-list" ref="search">
            </div>-->
            <div class="search-btn">
                <el-button icon="el-icon-search" @click="searchTable(true)">
                    搜索
                </el-button>
            </div>
        </div>
        <div class="" v-if="value!=0">
            <slot>
            </slot>
        </div>
    </div>
</template>
<style type="text/scss" lang="scss">
.e-select-list{
  .select-update{
    display: flex;
    flex-wrap:wrap;
    >div{
      margin-right: 10px;
    }
    >div:last-child{
      margin-right: 0px;
    }

  }
  .search-list{

  }
}
</style>
<script>

import api from "@/api";
import { empty, isArray ,isObject,isFunction} from "@/utils/extend";
import {findObject} from "@/utils/utils";

export default {
    name: "e-select-list",
    data() {
        return {
            list:[],
            searchMsg:{
                keyword:''
            }
        }
    },
    props:{
        value:[String,Number],
        model:{
            type:Object,
            required:true
        },
        module:{
            type:String,
            required: true
        },
        selectUpdate:[String,Array],
        searchUpdate:{
            type:[String,Array],
            default:''
        },
        showField:{
            type:[String,Array]
        },
        filters:{
            type:Function,
        }
    },
    watch: {
        value(val){
            this.selectOption(val);
        }
    },
    computed: {
    },
    methods: {
        selectOption(value){
            var v = findObject(this.list , r=>r.id == value );
            if( v !== false ){
                this.updateModel(v);
            }else{
                if(value == 0){
                    this.updateModel({});
                }else{
                    this.$post(api.search.table , {table:this.module,id:value}).then(res=>{
                        if(!empty(res) && isObject(res)){
                            this.list.push(res);

                            this.updateModel(res);
                        }
                    }).catch(err=>{
                        this.$message.error(err.message)
                    })
                }
            }
        },
        updateModel(v){
            var field = isArray(this.selectUpdate) ? this.selectUpdate : this.selectUpdate.split(',');
            for (var i=0;i<field.length;i++)
            {
                var f = field[i];
                if( f ){
                    var def = /(^[0-9]+$)/.test(this.model[f]) && this.model[f].length<11 ? 0 : '';
                    this.model[f] = v[f] !== undefined ? v[f] : def;
                }
            }
        },
        getLabel(v){
            var result = '';
            var field = [];
            if(this.showField){
                field = isArray(this.showField) ? this.showField : this.showField.split(',').filter(v=>!empty(v))
            }else{
                field = isArray(this.selectUpdate) ? this.selectUpdate : this.selectUpdate.split(',')
            }

            //var field = isArray(this.selectUpdate) ? this.selectUpdate : this.selectUpdate.split(',')

            for (var i=0;i<field.length;i++)
            {
                var f = field[i];
                if(f && v[f]){
                    result += v[f]+' - ';
                }
            }
            return result;
        },
        searchTable(isEmit){
            var where = {};
            var field = isArray(this.selectUpdate) ? this.selectUpdate : this.selectUpdate.split(',')
            var ext   = isArray(this.searchUpdate) ? this.searchUpdate : this.searchUpdate.split(',')
            var fs = field.concat(ext).filter(f=>f!='');
            where[fs.join('|')] = [ 'like' , '%'+this.searchMsg.keyword+'%'];
            this.$emit('update-where' , where);
            return new Promise((resolve, reject) => {
                this.$post(api.search.select , {
                    table:this.module,
                    where:JSON.stringify(where)
                }).then(res=>{
                    if(isEmit){
                        this.$emit('input' , 0);
                    }
                    if(isFunction(this.filters)){
                        this.list = this.filters(res);
                    }else{
                        this.list = res;
                    }
                    resolve(res)
                }).catch(err=>{
                    reject(err.message);
                });
            });
        }
    },
    created() {
        this.searchTable().then(res=>{
            if(this.value){
                this.selectOption(this.value)
            }
        });
    },
    mounted() {
    },
    destroyed() {
    }
}
</script>
