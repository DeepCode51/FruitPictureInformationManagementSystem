<template>
    <div class="admin-index">
        <div class="topbar-wrap white">
            <div class="topbar-inner clearfix">
                <div class="topbar-logo-wrap clearfix">
                    <h1 class="topbar-logo none"><a href="/admin/sy" class="navbar-brand">后台管理</a></h1>
                    <ul class="navbar-list clearfix">
                        <li><a class="on" href="/" style="vertical-align: middle">{{ setting.title }}</a></li>

                        <li><router-link to="/">网站首页</router-link></li>
                    </ul>
                </div>
                <div class="top-info-wrap">
                    <ul class="top-info-list clearfix">
                        <li><a href="javascript:;">{{ $session.username }} , {{$session.cx}}</a></li>
                        <li><router-link to="/admin/mod" target="main"> 修改密码 </router-link></li>
                        <li><a @click="logout" href="javascript:;">退出</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="container clearfix">
            <div class="sidebar-wrap">

                <div class="sidebar-content">
                    <ul class="sidebar-list" id="sidebarList">
                        <li v-for="(m,k) in menus" :class="{active:k==active}">
                            <a href="javascript:;" @click="active=k"><i class="icon-font">&#xe018;</i>{{m.label}}</a>
                            <ul class="sub-menu" v-show="k==active">
                                <li v-for="v in m.children"><router-link :to="v.to">{{v.label}}</router-link></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
            <!--/sidebar-->
            <div class="main-wrap">
                <router-view></router-view>
            </div>
            <!--/main-->
        </div>
    </div>
</template>
<style type="text/scss" scoped lang="scss">

    @font-face {
        font-family: 'icomoon';
        src: url('./fonts/icomoon.eot');
        src: url('./fonts/icomoon.eot?#iefix') format('embedded-opentype'), url('./fonts/icomoon.woff') format('woff'), url('./fonts/icomoon.ttf') format('truetype'), url('./fonts/icomoon.svg#icomoon') format('svg');
        font-weight: normal;
        font-style: normal;
    }

    .admin-index{
        height: 100%;
        width: 100%;
        .ppr {
            position: relative;
        }

        .ppa {
            position: absolute;
        }

        .fl {
            float: left
        }

        .fr {
            float: right
        }

        .tl {
            text-align: left;
        }

        .tc {
            text-align: center;
        }

        .tr {
            text-align: right;
        }

        .mlr5 {
            margin: 0 5px;
        }

        .mt5 {
            margin-top: 5px;
        }

        .mr5 {
            margin-right: 5px;
        }

        .mt10 {
            margin-top: 10px;
        }

        .ml5 {
            margin-left: 5px;
        }

        .ml10 {
            margin-left: 10px;
        }

        .mr10 {
            margin-right: 10px;
        }

        .mb10 {
            margin-bottom: 10px;
        }

        .mb20 {
            margin-bottom: 20px;
        }

        .pl5 {
            padding-left: 5px;
        }

        .pb10 {
            padding-bottom: 10px;
        }

        .pt20 {
            padding-top: 20px;
        }

        .pl10 {
            padding-left: 10px;
        }

        .ptb20 {
            padding: 20px 0;
        }

        .grayc {
            color: #ccc
        }

        .gray6 {
            color: #666
        }

        .gray9 {
            color: #999
        }

        .emstyle {
            font-style: italic
        }

        .clear {
            clear: both
        }

        .clearfix:after {
            content: ".";
            display: block;
            height: 0;
            clear: both;
            visibility: hidden;
        }

        .clearfix {
            *height: 1%;
        }

        .none {
            display: none
        }

        .block {
            display: block;
        }

        .minheight500 {
            min-height: 500px;
            height: auto !important;
            height: 500px;
            overflow: visible;
        }

        .wrap-box {
            width: 960px;
            margin: 0 auto;
        }

        .blue, .blue a:link, .blue a:visited {
            color: blue;
        }

        .blue a:hover {
            color: #f60;
        }

        .white, .white a:link, .white a:visited {
            color: #fff;
        }

        .white a:hover {
            color: #ff0;
        }

        .red, .red a:link, .red a:visited {
            color: #f00;
        }

        .red a:hover {
            color: #f60;
        }

        .orange, .orange a:link, .orange a:visited {
            color: #ffa500;
        }

        .orange a:hover {
            color: #f60;
        }



        .icon-font {
            font-family: 'icomoon';
            speak: none;
            font-weight: 400;
            font-style: normal;
            font-variant: normal;
            text-transform: none;
            line-height: 1;
            -webkit-font-smoothing: antialiased;
        }

        .require-red {
            color: #f60;
            font-family: serif;
            margin-right: 3px;
        }

        /* common */
        .topbar-wrap {
            height: 50px;
            line-height: 50px;
            background: #1A1A1A;
        }

        .topbar-logo-wrap {
            float: left;
        }

        .topbar-inner {
            font-size: 18px;
            padding: 0 20px;
        }

        .topbar-logo {
            float: left;
            width: 150px;
            text-align: center;
        }

        .navbar-list {
            float: left;
        }

        .top-info-wrap {
            float: right;
        }

        .navbar-list {
        }

        .navbar-list li {
            float: left;
        }

        .navbar-list li a {
            float: left;
            margin-right: 5px;
            padding: 0 25px;
            text-shadow: 1px 1px 1px #000;
        }

        .navbar-list li a:hover {
            background: #343434;
        }

        .navbar-list li a.on {
            color: #fff;
            text-shadow: 1px 1px 1px #003;
            background: #033b70;
        }

        .top-info-list li {
            float: left;
        }

        .top-info-list li a {
            float: left;
            margin-left: 5px;
            padding: 0 20px;
            display: inline;
            zoom: 1;
        }

        .top-info-list li a:hover {
            background: #343434;
        }

        .btn {
            display: inline-block;
            *display: inline;
            padding: 4px 12px;
            margin-bottom: 0;
            *margin-left: .3em;
            font-size: 14px;
            line-height: 20px;
            color: #333333;
            text-align: center;
            text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);
            vertical-align: middle;
            cursor: pointer;
            background-color: #f5f5f5;
            *background-color: #e6e6e6;
            background-image: -moz-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#ffffff), to(#e6e6e6));
            background-image: -webkit-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: -o-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: linear-gradient(to bottom, #ffffff, #e6e6e6);
            background-repeat: repeat-x;
            border: 1px solid #bbbbbb;
            border-color: #e6e6e6 #e6e6e6 #bfbfbf;
            border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
            border-bottom-color: #a2a2a2;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            border-radius: 4px;
            -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
            -moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
            box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
        }

        .btn:hover, .btn:active, .btn.active, .btn.disabled, .btn[disabled] {
            color: #333333;
            background-color: #e6e6e6;
            *background-color: #d9d9d9;
        }

        .btn:active, .btn.active {
            background-color: #cccccc \9;
        }

        .btn:first-child {
            *margin-left: 0;
        }

        .btn:hover {
            color: #333333;
            text-decoration: none;
            background-position: 0 -15px;
            -webkit-transition: background-position 0.1s linear;
            -moz-transition: background-position 0.1s linear;
            -o-transition: background-position 0.1s linear;
            transition: background-position 0.1s linear;
        }

        .btn:focus {
            outline: thin dotted #333;
            outline: 5px auto -webkit-focus-ring-color;
            outline-offset: -2px;
        }

        .btn.active, .btn:active {
            background-image: none;
            outline: 0;
            -webkit-box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
            -moz-box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
            box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
        }

        .btn-primary {
            color: #ffffff;
            text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
            background-color: #006dcc;
            *background-color: #0044cc;
            background-image: -moz-linear-gradient(top, #0088cc, #0044cc);
            background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#0088cc), to(#0044cc));
            background-image: -webkit-linear-gradient(top, #0088cc, #0044cc);
            background-image: -o-linear-gradient(top, #0088cc, #0044cc);
            background-image: linear-gradient(to bottom, #0088cc, #0044cc);
            background-repeat: repeat-x;
            border-color: #0044cc #0044cc #002a80;
            border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
        }

        .btn-primary:hover, .btn-primary:active, .btn-primary.active, .btn-primary.disabled, .btn-primary[disabled] {
            color: #ffffff;
            background-color: #0044cc;
            *background-color: #003bb3;
        }

        .btn-primary:active, .btn-primary.active {
            background-color: #003399 \9;
        }

        .btn-warning {
            color: #ffffff;
            text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
            background-color: #faa732;
            *background-color: #f89406;
            background-image: -moz-linear-gradient(top, #fbb450, #f89406);
            background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#fbb450), to(#f89406));
            background-image: -webkit-linear-gradient(top, #fbb450, #f89406);
            background-image: -o-linear-gradient(top, #fbb450, #f89406);
            background-image: linear-gradient(to bottom, #fbb450, #f89406);
            background-repeat: repeat-x;
            border-color: #f89406 #f89406 #ad6704;
            border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
        }

        .btn-warning:hover, .btn-warning:active, .btn-warning.active, .btn-warning.disabled, .btn-warning[disabled] {
            color: #ffffff;
            background-color: #f89406;
            *background-color: #df8505;
        }

        .btn-warning:active, .btn-warning.active {
            background-color: #c67605 \9;
        }

        .btn-danger {
            color: #ffffff;
            text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
            background-color: #da4f49;
            background-image: linear-gradient(to bottom, #ee5f5b, #bd362f);
            background-repeat: repeat-x;
            border-color: #bd362f #bd362f #802420;
            border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
        }

        .btn-danger:hover, .btn-danger:active, .btn-danger.active, .btn-danger.disabled, .btn-danger[disabled] {
            color: #ffffff;
            background-color: #bd362f;
            *background-color: #a9302a;
        }

        .btn-danger:active, .btn-danger.active {
            background-color: #942a25 \9;
        }

        .btn-success {
            color: #ffffff;
            text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
            background-color: #5bb75b;
            background-image: linear-gradient(to bottom, #62c462, #51a351);
            background-repeat: repeat-x;
            border-color: #51a351 #51a351 #387038;
            border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
        }

        .btn-success:hover, .btn-success:active, .btn-success.active, .btn-success.disabled, .btn-success[disabled] {
            color: #ffffff;
            background-color: #51a351;
        }

        .btn-success:active, .btn-success.active {
            background-color: #408140 \9;
        }

        .btn-info {
            color: #ffffff;
            text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
            background-color: #49afcd;
            *background-color: #2f96b4;
            background-image: linear-gradient(to bottom, #5bc0de, #2f96b4);
            background-repeat: repeat-x;
            border-color: #2f96b4 #2f96b4 #1f6377;
            border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
        }

        .btn-info:hover, .btn-info:active, .btn-info.active, .btn-info.disabled, .btn-info[disabled] {
            color: #ffffff;
            background-color: #2f96b4;
            *background-color: #2a85a0;
        }

        .btn-info:active, .btn-info.active {
            background-color: #24748c \9;
        }

        .btn-inverse {
            color: #ffffff;
            text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25);
            background-color: #363636;
            *background-color: #222222;
            background-image: linear-gradient(to bottom, #444444, #222222);
            background-repeat: repeat-x;
            border-color: #222222 #222222 #000000;
            border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
        }

        .btn-inverse:hover, .btn-inverse:active, .btn-inverse.active, .btn-inverse.disabled, .btn-inverse[disabled] {
            color: #ffffff;
            background-color: #222222;
            *background-color: #151515;
        }

        .btn-inverse:active, .btn-inverse.active {
            background-color: #080808 \9;
        }

        .btn4 {
            width: 60px;
        }

        .btn5 {
            width: 80px;
        }

        .btn6 {
            width: 120px;
        }

        .btn7 {
            width: 130px;
        }

        .btn8 {
            width: 140px;
        }

        .btn9 {
            width: 160px;
        }

        .btn10 {
            width: 180px;
        }

        .btn50 {
            width: 50%;
        }

        .btn100 {
            width: 100%;
        }

        textarea, input[type="text"], input[type="password"], input[type="datetime"], input[type="datetime-local"], input[type="date"], input[type="month"], input[type="time"], input[type="week"], input[type="number"], input[type="email"], input[type="url"], input[type="search"], input[type="tel"], input[type="color"], .uneditable-input {
            background-color: #ffffff;
            border: 1px solid #cccccc;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
            -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
            -webkit-transition: border linear 0.2s, box-shadow linear 0.2s;
            -moz-transition: border linear 0.2s, box-shadow linear 0.2s;
            -o-transition: border linear 0.2s, box-shadow linear 0.2s;
            transition: border linear 0.2s, box-shadow linear 0.2s;
        }

        textarea:focus, input[type="text"]:focus, input[type="password"]:focus, input[type="datetime"]:focus, input[type="datetime-local"]:focus, input[type="date"]:focus, input[type="month"]:focus, input[type="time"]:focus, input[type="week"]:focus, input[type="number"]:focus, input[type="email"]:focus, input[type="url"]:focus, input[type="search"]:focus, input[type="tel"]:focus, input[type="color"]:focus, .uneditable-input:focus {
            border-color: rgba(82, 168, 236, 0.8);
            outline: 0;
            outline: thin dotted \9; /* IE6-9 */
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
            -moz-box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px rgba(82, 168, 236, 0.6);
        }

        .insert-tab {
            border-collapse: collapse;
            border: 1px solid #eee;
        }

        .insert-tab th, .insert-tab td {
            border: 1px solid #eee;
        }

        .insert-tab tr {
            line-height: 40px;
        }

        .insert-tab th {
            text-align: right;
            font-weight: normal;
            padding-right: 10px;
            font-size: 14px;
        }

        .insert-tab td {
            text-align: left;
            padding-left: 10px;
        }

        .list-page {
            padding: 20px 0;
            text-align: center;
        }

        .list-page a {
            margin: 0 5px;
            padding: 2px 7px;
            border: 1px solid #ccc;
            background: #f3f3f3;
        }

        .list-page a:hover {
            background: #e4e4e4;
            border: 1px solid #908f8f;
        }

        .list-page .current {
            margin: 0 5px;
            padding: 2px 7px;
            background: #f60;
            border: 1px solid #fe8101;
            color: #fff;
        }

        .sidebar-wrap {
            position: absolute;
            left: 0px;
            top: 50px;
            bottom: 0px;
            overflow: hidden;
            overflow-y: auto;
            width: 189px;

            background: #f2f2f2;
            border-right: 1px solid #ccc; /*cursor:e-resize;*/
        }
        .container{
            height: calc(100% - 50px);
        }
        .main-wrap {
            margin-left: 190px;
            height: 100%;
            overflow: hidden;
        }
        .iframe{
            height: 100%;
            width: 100%;
            padding: 5px;
            overflow: hidden;
            border: none;
            overflow-y: auto;
            box-sizing: border-box;
        }

        .sidebar-title {
            height: 40px;
            line-height: 40px;
            text-indent: 1em;
            font-size: 24px;
            text-align: left;
        }




        .sidebar-title h1 {
            font-weight: normal;
        }

        .sidebar-content {
            padding-top: 5px;
        }

        .sidebar-list li {
            border: 1px solid #e5e5e5;
            border-width: 1px 0;
            transition: .3s linear;
        }

        .sidebar-list li a{
            transition: .3s linear;
        }

        .sidebar-list>li.active{
            background: #3b3b3b;
            color: #FFFFFF;
        }


        .sidebar-list>li.active>a{
            color: #ffffff;
            position: relative;
            overflow: hidden;
        }
        .sidebar-list>li.active>a:after{
            content: ' ';
            width:0;
            height:0;
            border-right:38px solid transparent;
            border-left:38px solid transparent;
            border-top:38px solid #f2f2f2;
            position: absolute;
            right: -38px;
            top: 0px;
            z-index: 1;
        }


        .sidebar-list>li.active>a:hover{
            background: #000000;
            color: #ffffff;
        }

        .sidebar-list li .icon-font {
            margin-right: 5px;
            color: #888;
            font-size: 14px;
        }

        .sidebar-list li a {
            padding: 0 16px 0 20px;
            display: block;
            height: 38px;
            line-height: 38px;
            color: #333;
        }

        .sidebar-list li a:hover {
            background: #fff;
            color: #1963AA;
        }

        .sub-menu {
            display: none;
            border-top: 1px solid #e5e5e5;
            background: #fff;
        }



        .sub-menu li {
            padding-left: 21px;
        }

        .sub-menu li.on {
            background: #1963AA;
        }

        .sub-menu li.on a {
            background: #1963AA;
            color: #fff;
        }

        .sub-menu li.on .icon-font {
            color: #fff;
        }

        .sub-menu .icon-font {
            font-size: 11px;
        }

        /* main */
        .crumb-wrap {
            height: 40px;
            line-height: 39px;
            border-bottom: 1px solid #e5e5e5;
            background: #f5f5f5;
        }

        .crumb-list {
            padding-left: 12px;
        }

        .crumb-list .icon-font {
            margin-right: 5px;
        }

        .crumb-step {
            margin: 0 5px;
            color: #b2c2e0;
            font-family: serif;
        }

        .search-wrap {
            padding: 15px 0;
            border-bottom: 1px solid #e5e5e5;
        }

        .search-tab tr {
            line-height: 35px;
        }

        .search-tab th {
            text-align: right;
            padding-right: 10px;
            font-weight: normal;
        }

        .search-tab td {
            padding: 0 5px;
        }

        .common-text {
            height: 23px;
            line-height: 23px;
            padding: 2px 4px;
            border: 1px solid #ccc;
            background: #fff;
            border-radius: 5px;
        }

        .result-wrap {
            padding: 10px 20px;
            border-bottom: 1px solid #e5e5e5;
        }

        .result-title {
            line-height: 35px;
            padding-bottom: 5px;
            overflow: hidden;
        }

        .comment-title {
            line-height: 35px;
            margin-bottom: 10px;
            overflow: hidden;
        }

        .result-list a {
            margin-right: 15px;
        }

        .result-list i {
            padding-right: 5px;
        }

        .result-tab {
            border-collapse: collapse;
            border: 1px solid #ddd;
        }

        .result-tab th {
            font-weight: normal;
            font-size: 15px;
            text-align: left;
            background: url(./images/tab-thbg.png) #f9f9f9 0 bottom repeat-x;
        }

        .result-tab th, .result-tab td {
            padding: 5px;
            border-bottom: 1px solid #ddd;
            border-right: 1px solid #e1e1e1;
        }

        .result-tab tr {
            line-height: 35px;
        }

        .result-tab th.tc, .result-tab td.tc {
            text-align: center;
        }

        .result-tab tr:nth-child(odd) {
            background: #f9f9f9;
        }

        .result-tab tr:hover {
            background: #f1f1f1;
        }

        .result-tab tr.even-tr {
            background: #e9fff2;
        }

        .comment-tab {
            border-bottom: 1px solid #ddd;
            padding-left: 15px;
            margin-bottom: 10px;
        }

        .comment-tab a {
            float: left;
        }

        .comment-tab a.on {
            padding: 0 15px;
            background: #49afcd;
            color: #fff;
            border-radius: 5px 5px 0 0;
        }

        /* config */
        .config-items {
            margin-bottom: 25px;
        }

        .config-title {
            margin-bottom: 10px;
        }

        .config-title h1 {
            font-size: 14px;
            font-weight: normal;
        }

        .config-title h1 i {
            margin-right: 5px;
            font-size: 12px;
        }

        .columns-title {
            padding: 10px 0;
            height: 35px;
            line-height: 35px;
            overflow: hidden;
        }

        /*.columns-title .comment-tab{padding-left: 0;}*/
        .visitor-img {
            float: left;
        }

        .visitor-info {
            float: left;
            padding-left: 10px;
            line-height: 23px;
        }

        .visitor-email {
            color: #aaa;
        }

        .short-wrap a {
            margin-right: 20px;
        }

        .short-wrap a i {
            margin-right: 5px;
        }

        .res-lab {
            display: inline-block;
            width: 150px;
            padding-right: 10px;
            text-align: right;
        }

        .sys-info-list li {
            line-height: 35px;
            border-bottom: 1px dashed #eaeaea;
        }

        .res-lab {
            color: #909090;
        }

        .res-info {
            font-size: 15px;
        }

        .sort-input {
            width: 25px;
            text-align: center;
        }

        .sidebar-list>li.active .sub-menu{
            display: block;
        }
        .main-wrap{
            position: absolute;
            top: 50px;
            right: 0px;
            bottom: 0px;
            left: 190px!important;
            height: auto;
            margin-left: 0px!important;
            padding: 20px;
            overflow: hidden auto!important;

        }

    }
</style>
<script>
    var setting = require("@/setting");
    import api from "@/api";
    import menu from "@/views/admin/sidebar";

    export default {
        name: "admin-index",
        data() {
            return {
                setting,
                active: 0,
            };
        },
        watch: {},
        computed: {
            menus() {
                var cx = this.$session.cx;
                return menu[cx];
            },
        },
        methods: {
            logout() {
                this.$confirm("确定退出登录？", "确认信息").then(() => {
                    // 退出登录
                    this.$store
                        .dispatch("user/logout")
                        .then((res) => {
                            if (res.code == api.code.OK) {
                                this.$message.success("退出登录成功");
                                this.$router.replace("/index");
                            } else {
                                this.$message.error(res.msg);
                            }
                        })
                        .catch((err) => {
                            this.$message.error(err.message);
                        });
                });
            },
        },
        created() {},
        mounted() {},
        destroyed() {},
    };
</script>
