<template>
  <div class="dashboard">
    <Sidebar :username="username" />
    <main>
      <h1>Welcome, {{ username }}</h1>
      <h2>Last 10 Transactions</h2>
      <TransactionTable />
      <h2>Ethereum Price Chart</h2>
      <EthPriceChart />
    </main>
  </div>
</template>

<script>
import Sidebar from '@/components/Sidebar.vue';
import TransactionTable from '@/components/TransactionTable.vue';
import EthPriceChart from '@/components/EthPriceChart.vue';

export default {
  name: 'DashBoard',
  components: {
    Sidebar,
    TransactionTable,
    EthPriceChart
  },
  data() {
    return {
      loggedIn: false, // Initially not logged in, adjust as per your actual login logic
      username: 'Amjad Khaliliah' // Replace with actual username retrieval
    };
  },
};
</script>

<style scoped>
.dashboard {
  display: flex;
}

main {
  flex: 1;
  padding: 20px;
}
</style>
