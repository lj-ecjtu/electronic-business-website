<template>
    <div class="product">
        <router-link :to="'/product/' + info.id" class="product-main">
            <img :src="info.pic">
            <h4>{{ info.name }}</h4>
            <!--<div class="product-color" :style="{ background: colors[info.color]}"></div>-->
            <div class="product-cost">¥ {{ info.price }}</div>
            <div class="product-add-cart" @click.prevent="handleCart">加入购物车</div>
        </router-link>
    </div>
</template>
<script>
    export default {
    	// 声明需要从父级接收数据
        props: {
            info: Object
        },
        methods: {
            handleCart () {
                this.$store.commit('addCart', this.info.id);
            }
        }
    };
</script>
<!-- 以下样式只作用于当前的.vue文件 -->
<style scoped>
    .product{
        width: 25%;
        float: left;
    }
    .product-main{
        display: block;
        margin: 16px;
        padding: 16px;
        border: 1px solid #dddee1;
        border-radius: 6px;
        overflow: hidden;
        background: #fff;
        text-align: center;
        position: relative;
    }
    .product-main img{
        width: 100%;
    }
    h4{
        color: #222;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
    .product-main:hover h4{
        color: #0070c9;
    }
    .product-cost{
        color: #de4037;
        margin-top: 6px;
    }
    .product-add-cart{
        display: none;
        padding: 4px 8px;
        background: #2d8cf0;
        color: #fff;
        font-size: 12px;
        border-radius: 3px;
        cursor: pointer;
        position: absolute;
        top: 5px;
        right: 5px;
    }
    .product-main:hover .product-add-cart{
        display: inline-block;
    }
</style>