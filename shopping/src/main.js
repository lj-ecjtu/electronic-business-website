import Vue from 'vue';
import VueRouter from 'vue-router';
import Routers from './router/router';
import Vuex from 'vuex';
import App from './App.vue';
import './style.css';
import axios from 'axios'

Vue.use(VueRouter);
Vue.use(Vuex);

// 路由配置
const RouterConfig = {
	// 使用 HTML5 的 History 路由模式
	mode: 'history',
	routes: Routers
};
const router = new VueRouter(RouterConfig);

/*
1、router.beforeEach(function(to,form,next){}) 在跳转之前执行
2.router.afterEach(function(to,form)) 在跳转之后判断

to:   router即将进入的路由对象
from: 当前导航即将离开的路由
next: Function,进行管道中的一个钩子，如果执行完了，则导航的状态就是 confirmed （确认的）；否则为false，终止导航。
afterEach函数不用传next()函数
*/

router.beforeEach((to, from, next) => {
	window.document.title = to.meta.title;
	next();
});

router.afterEach((to, from, next) => {
	window.scrollTo(0, 0);
});

// 数组去重
function getFilterArray(array) {
	const res = [];
	const json = {};
	for(let i = 0; i < array.length; i++) {
		const _self = array[i];
		if(!json[_self]) {
			res.push(_self);
			json[_self] = 1;
		}
	}
	return res;
}

const store = new Vuex.Store({
	state: {
		// 商品列表
		productList: [],
		// 购物车列表
		cartList: []
	},
	getters: {
		brands: state => {
			const brands = state.productList.map(item => item.brand);
			return getFilterArray(brands);
		}
	},
	mutations: {
		// 添加商品列表
		setProductList(state, data) {
			state.productList = data;
		},
		// 添加到购物车
		addCart(state, id) {
			// 先判断购物车是否已有，如果有，数量+1，如果没有，则新建订单
			/*
			find() 方法返回通过测试（函数内判断）的数组的第一个元素的值。
			find() 方法为数组中的每个元素都调用一次函数执行：
			当数组中的元素在测试条件时返回 true 时, find() 返回符合条件的元素，之后的值不会再调用执行函数。
			如果没有符合条件的元素返回 undefined
			*/
			const isAdded = state.cartList.find(item => item.id === id);
			if(isAdded) {
				isAdded.count++;
			} else {
				state.cartList.push({
					id: id,
					count: 1
				})
			}
		},
		// 修改购物车中商品数量
		editCartCount(state, payload) {
			const product = state.cartList.find(item => item.id === payload.id);
			product.count += payload.count;
		},
		// 删除购物车中商品
		deleteCart(state, id) {
			const index = state.cartList.findIndex(item => item.id === id);
			state.cartList.splice(index, 1);
		},
		// 清空购物车
		emptyCart(state) {
			state.cartList = [];
		}
	},
	//在list.vue中调用
	actions: {
		// 请求商品列表
		getProductList(context) {
			/*
			AJAX = 异步 JavaScript 和 XML。
			AJAX 是一种用于创建快速动态网页的技术。
			通过在后台与服务器进行少量数据交换，AJAX 可以使网页实现异步更新。这意味着可以在不重新加载整个网页的情况下，对网页的某部分进行更新。
			传统的网页（不使用 AJAX）如果需要更新内容，必需重载整个网页面。
			*/
		    // 通过 ajax 获取   ，此处的result代表ajax请求的相应
			axios.get('/eshop/product/getProduct').then(function(result) {
				context.commit('setProductList', result.data);
			})			
		}
	}
});

new Vue({
	el: '#app',
	router: router,
	store: store,
	render: h => {
		return h(App)
	}
});