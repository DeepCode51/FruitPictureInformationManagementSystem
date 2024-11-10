<template>
    <div class="e-container">
        <slot></slot>
    </div>
</template>
<style type="text/scss" lang="scss">
@media screen and (min-width: 1100px){
  .e-container{
    width: 980px;
    margin:0 auto;
  }
}
@media screen and (min-width: 1200px){
  .e-container{
    width: 1100px;
    margin:0 auto;
  }
}
@media screen and (min-width: 1300px){
  .e-container{
    width: 1200px;
    margin:0 auto;
  }
}

@media screen and (max-width: 768px) {
  .e-container{
    padding: 0px 8px;
  }
  #app{
    overflow-x: hidden;
  }
  #project-box .info{
    float: none!important;
  }
  #project-box .entry-content{
    float: none!important;
    width: 100% !important;
    padding: 10px;
  }
  .el-form{
    .el-form-item__label{
      width: 100% !important;
      text-align: left;
    }
    .el-form-item__content{
      width: 100%!important;
      margin-left: 0px!important;
    }
  }
  p{
    white-space:pre-wrap;
  }
  .Home >div>div>.el-carousel{
    display: none;
  }
  .goods-info {
    .gallery-list{
      float: none!important;
      width: 100% !important;
      height: 350px;
    }

    .goods-right-content{
      margin-left: 0px!important;
    }
  }
  .hjlgf{
    .row1{
      float: none!important;
      width: auto;
    }
    .row2{
      margin-left: 0px!important;
    }
  }

}
</style>
<script>
    export default {
        name: "e-container",
    }
</script>
