<!-- src/views/Home.vue -->
<template>

  <div class="home-container">
    <div class="home">
      <h1>Home Page</h1>
      <p>Welcome to Amjad Khaliliah Wallet</p>
    </div>

    <div id="chart">
      <EthPriceChart/>
    </div>
  </div>
</template>

<script>
import EthPriceChart from "@/components/EthPriceChart.vue";


export default {
  name: 'HomePage',
  components: { EthPriceChart},
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
* {
  font-family: Poppins, sans-serif;
  margin: 0;
}

body {
  align-content: center;
  align-items: center;
}

.home-container {
  display: grid;
  place-items: center; /* Center both horizontally and vertically */
  height: 70vh; /* Full viewport height */
}

.home {
  text-align: center;
}

#chart{
  width: 70%;
}
</style>
