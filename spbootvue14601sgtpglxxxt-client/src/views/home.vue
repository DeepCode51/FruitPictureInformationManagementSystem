<template>
    <div class="Home">
        <v-header></v-header>
        <transition name="slide-slip">
            <router-view />
        </transition>
        <v-footer></v-footer>
    </div>
</template>
<style type="text/scss" lang="scss">
    .slide-slip-enter-active,
    .slide-slip-leave-active {
        transition: all 0.3s;
    }
    .slide-slip-enter {
        transform: translateX(100%);
        /* opacity: 0; */
    }
    .slide-slip-leave-to {
        transform: translateX(-100%);
    }
    .dye-bottom-leave-active,
    .dye-bottom-enter-active {
        transition: all 0.3s;
    }
    .dye-bottom-enter,
    .dye-bottom-leave-to {
        opacity: 0.5;
    }
</style>
<script>
    import VHeader from "@/views/header";
    import VFooter from "@/views/footer";

    export default {
        name: "home",
        components: { VFooter, VHeader },
        data() {
            return {};
        },
        watch: {},
        computed: {},
        methods: {},
        created() {},
        mounted() {},
        destroyed() {},
    };
</script>
