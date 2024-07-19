<template>
  <table class="table">
    <thead>
    <tr>
      <th>Date</th>
      <th>Sender</th>
      <th>Recipient</th>
      <th>Amount</th>
      <th>Type</th>
      <th>Description</th>
    </tr>
    </thead>
    <tbody>
    <!-- Iterate over transactions here -->
    <tr v-for="(transaction, index) in transactions" :key="index">
      <td>{{ transaction.date }}</td>
      <td>{{ transaction.sender }}</td>
      <td>{{ transaction.amount }}</td>
      <td>{{ transaction.type }}</td>
      <td>{{ transaction.description }}</td>
    </tr>
    </tbody>
  </table>
</template>

<script>
export default {
  name: 'TransactionTable',
  data() {
    return {
      transactions: [] // Initialize with actual data from API
    };
  },
  mounted() {
    // Fetch last 10 transactions from API
    this.fetchTransactions();
  },
  methods: {
    async fetchTransactions() {
      try {
        const response = await fetch('http://localhost:9090/api/transactions');
        if (response.ok) {
          const data = await response.json();
          this.transactions = data.slice(0, 10); // Assuming API returns an array of transactions
        } else {
          console.error('Failed to fetch transactions');
        }
      } catch (error) {
        console.error('An error occurred while fetching transactions:', error);
      }
    }
  }
};
</script>

<style scoped>
.table {
  width: 100%;
  border-collapse: collapse;
}

.table th,
.table td {
  border: 1px solid #ccc;
  padding: 8px;
}

.table th {
  background-color: #f2f2f2;
  font-weight: bold;
  text-align: left;
}
</style>
