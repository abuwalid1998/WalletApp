<!-- src/components/NavBar.vue -->
<template>
  <nav v-if="isLoggedIn" class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Wallet App</a>
      <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <router-link class="nav-link" to="/">Home</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/Registration">Register</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/login">Login</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/buycoins">Buy Coins</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/sellcoins">Sell Coins</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/tratable">Transactions Table</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/watch">Watch Balances</router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: 'NavBar',
  computed: {
    isLoggedIn() {
      const token = localStorage.getItem('token');
      console.log(token);
      return localStorage.getItem('accessToken') != null && localStorage.getItem('accessToken') !== '';
    }
  },
};
</script>


<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap');

* {
  font-family: Poppins, sans-serif;
}
</style>
