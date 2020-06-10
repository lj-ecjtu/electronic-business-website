<template>
    <div v-show="list.length">    	
        <div class="list-control">
        	<!-- 品牌筛选按钮 -->
            <div class="list-control-filter">
                <span>品牌：</span>
                <span
                    class="list-control-filter-item"
                    :class="{on: item === filterBrand}"
                    v-for="item in brands"
                    @click="handleFilterBrand(item)">{{ item }}</span>
            </div>     
            <!-- 价格排序按钮 -->
            <div class="list-control-order">
                <span>排序：</span>
                <span
                    class="list-control-order-item"
                    :class="{on: order === ''}"
                    @click="handleOrderDefault">默认</span>               
                <span
                    class="list-control-order-item"
                    :class="{on: order.indexOf('price') > -1}"
                    @click="handleOrderCost">
                                                  价格
                    <template v-if="order === 'price-asc'">↑</template>
                    <template v-if="order === 'price-desc'">↓</template>
                </span>
            </div>
        </div>        
        
        <!-- 遍历商品列表，构建多个components/product.vue组件-->
        <Product v-for="item in orderedAndFilteredList" :info="item" :key="item.id"></Product>
        <div class="product-not-found" v-show="!orderedAndFilteredList.length">暂无相关商品</div>
    </div>
</template>
<script>
	// 导入单个商品展示组件
    import Product from '../components/product.vue';
    export default {
        components: { Product },
		//计算属性
        computed: {
            list () {
            	// 从Vuex获取商品列表数据
                return this.$store.state.productList;
            },
            brands () {
            	
                return this.$store.getters.brands;
            },
            orderedAndFilteredList () {
            	// 复制原始数据
                let list = [...this.list];
                // 按品牌过滤
                if (this.filterBrand !== '') {
                    list = list.filter(item => item.brand === this.filterBrand);
                }           
                // 排序
                if (this.order !== '') {
                    if (this.order === 'price-desc') {
                        list = list.sort((a, b) => b.price - a.price);
                    } else if (this.order === 'price-asc') {
                        list = list.sort((a, b) => a.price - b.price);
                    }
                }
                return list;
            }
        },
        data () {
            return {
            	// 过滤依据
                filterBrand: '',
                // 排序依据，取值有price-desc：价格降序， price-asc：价格升序
                order: ''
            }
        },
        methods: {
            handleFilterBrand (brand) {
				//当已选中某个过滤品牌后，再点击一下便取消品牌筛选
				//如果本身没有选中每个某牌，则根据品牌进行筛选
                if (this.filterBrand === brand) {
                    this.filterBrand = '';
                } else {
                    this.filterBrand = brand;
                }
            },            
            handleOrderDefault () {
                this.order = '';
            },
			//在降序/升序之间进行切换
            handleOrderCost () {
                if (this.order === 'price-desc') {
                    this.order = 'price-asc';
                } else {
                    this.order = 'price-desc';
                }
            }
        },
		//Vue声明周期钩子函数
        mounted () {
        	// 初始化时，通过Vuex的action请求数据
            this.$store.dispatch('getProductList');
        }
    }
</script>
<style scoped>
    .list-control{
        background: #fff;
        border-radius: 6px;
        margin: 16px;
        padding: 16px;
        box-shadow: 0 1px 1px rgba(0,0,0,.2);
    }
    .list-control-filter{
        margin-bottom: 16px;
    }
    .list-control-filter-item,
    .list-control-order-item {
        cursor: pointer;
        display: inline-block;
        border: 1px solid #e9eaec;
        border-radius: 4px;
        margin-right: 6px;
        padding: 2px 6px;
    }
    .list-control-filter-item.on,
    .list-control-order-item.on{
        background: #f2352e;
        border: 1px solid #f2352e;
        color: #fff;
    }
    .product-not-found{
        text-align: center;
        padding: 32px;
    }
</style>