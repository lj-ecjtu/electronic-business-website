// 引入组件
import cart from "../views/cart.vue";
import list from "../views/list.vue";
import product from "../views/product.vue";

const routers = [{
		path: '/list',
		meta: {
			title: '商品列表'
		},
		component: list
	},
	{
		path: '/product/:id',    //商品详情页，views/product.vue
		meta: {
			title: '商品详情'
		},
		component: product
	},
	{
		path: '/cart',
		meta: {
			title: '购物车'
		},
		component: cart
	},
	{
		path: '*',
		redirect: '/list'
	}
];
export default routers;