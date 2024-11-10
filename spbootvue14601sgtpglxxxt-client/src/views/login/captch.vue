<template>
    <canvas ref="canvas" @click="updateCode" :style="{width: contentWidth+'px',height:contentHeight+'px'}" :width="contentWidth" :height="contentHeight"></canvas>
</template>
<script>
    export default {
        name: "v-captch",
        props: {
            value: {
                type: String,
            },
            fontSizeMin: {
                type: Number,
                default: 25,
            },
            fontSizeMax: {
                type: Number,
                default: 30,
            },
            backgroundColorMin: {
                type: Number,
                default: 255,
            },
            backgroundColorMax: {
                type: Number,
                default: 255,
            },
            colorMin: {
                type: Number,
                default: 0,
            },
            colorMax: {
                type: Number,
                default: 160,
            },
            lineColorMin: {
                type: Number,
                default: 100,
            },
            lineColorMax: {
                type: Number,
                default: 255,
            },
            dotColorMin: {
                type: Number,
                default: 0,
            },
            dotColorMax: {
                type: Number,
                default: 255,
            },
            contentWidth: {
                type: Number,
                default: 112,
            },
            contentHeight: {
                type: Number,
                default: 31,
            },
            codeNumber: {
                type: Number,
                default: 4,
            },
        },
        methods: {
            // 生成一个随机数
            randomNum(min, max) {
                return Math.floor(Math.random() * (max - min) + min);
            },
            // 生成一个随机的颜色
            randomColor(min, max) {
                let r = this.randomNum(min, max);
                let g = this.randomNum(min, max);
                let b = this.randomNum(min, max);
                return "rgb(" + r + "," + g + "," + b + ")";
            },
            drawPic() {
                let canvas = this.$refs.canvas; //document.getElementById('s-canvas')
                let ctx = canvas.getContext("2d");
                ctx.textBaseline = "bottom";
                // 绘制背景
                ctx.fillStyle = this.randomColor(this.backgroundColorMin, this.backgroundColorMax);
                ctx.fillRect(0, 0, this.contentWidth, this.contentHeight);
                // 绘制文字
                for (let i = 0; i < this.value.length; i++) {
                    this.drawText(ctx, this.value[i], i);
                }
                this.drawLine(ctx);
                this.drawDot(ctx);
            },
            drawText(ctx, txt, i) {
                ctx.fillStyle = this.randomColor(this.colorMin, this.colorMax);
                ctx.font = "16px SimHei";
                let x = i * (this.contentWidth / (this.value.length + 1));

                let y = this.randomNum(this.fontSizeMax, this.contentHeight - 5);

                var deg = 0; //this.randomNum(-45, 45)
                // 修改坐标原点和旋转角度
                ctx.translate(x, y);
                ctx.rotate((deg * Math.PI) / 180);
                ctx.fillText(txt, 0, 0);
                // 恢复坐标原点和旋转角度
                ctx.rotate((-deg * Math.PI) / 180);
                ctx.translate(-x, -y);
            },
            drawLine(ctx) {
                // 绘制干扰线
                /* for (let i = 0; i < 5; i++) {
                    ctx.strokeStyle = this.randomColor(this.lineColorMin, this.lineColorMax)
                    ctx.beginPath()
                    ctx.moveTo(this.randomNum(0, this.contentWidth), this.randomNum(0, this.contentHeight))
                    ctx.lineTo(this.randomNum(0, this.contentWidth), this.randomNum(0, this.contentHeight))
                    ctx.stroke()
                }*/
            },
            drawDot(ctx) {
                // 绘制干扰点
                /*for (let i = 0; i < 80; i++) {
                    ctx.fillStyle = this.randomColor(0, 255)
                    ctx.beginPath()
                    ctx.arc(this.randomNum(0, this.contentWidth), this.randomNum(0, this.contentHeight), 1, 0, 2 * Math.PI)
                    ctx.fill()
                }*/
            },
            updateCode() {
                this.$emit("input", this.getCode());
            },
            getCode() {
                var result = "";
                for (var i = 0; i < this.codeNumber; i++) {
                    result += this.randomNum(0, 10);
                }
                return result;
            },
        },
        watch: {
            value() {
                this.drawPic();
            },
        },
        mounted() {
            if (this.value == "") {
                this.$emit("input", this.getCode());
            } else {
                this.drawPic();
            }
            //this.drawPic()
        },
    };
</script>
<style scoped>
    .s-canvas {
        height: 38px;
        position: absolute;
        right: 0px;
        top: 0px;
    }
    .s-canvas canvas {
        margin-top: 1px;
        margin-left: 8px;
    }
</style>
