// src/router/index.js
import { createRouter, createWebHistory } from 'vue-router';
import Login from "@/Views/Login.vue";
import Home from "@/components/Home.vue";
import Register from "@/Views/Register.vue";
import BuyCoins from "@/Views/BuyCoins.vue";




const routes = [
    { path: '/', component: Home },
    { path: '/login', component: Login },
    { path: '/Registration', component: Register },
    { path: '/buycoins', component: BuyCoins },

];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
