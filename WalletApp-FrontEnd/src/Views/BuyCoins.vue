<!-- src/views/BuyCoins.vue -->
<template>
  <div class="buy-coins">
    <div class="content">
      <h2>Buy Coins</h2>
      <form @submit.prevent="handleBuyCoins">
        <div class="form-group">
          <label for="walletId">Wallet ID:</label>
          <input type="text" id="walletId" v-model="walletId" class="form-control" readonly>
        </div>
        <div class="form-group">
          <label for="amount">Amount:</label>
          <input type="number" id="amount" v-model="amount" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Buy</button>
      </form>
      <div v-if="responseMessage" class="response-message">{{ responseMessage }}</div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import NavBar from "@/components/NavBar.vue";

export default {
  name: 'BuyCoins',
  components: {
    // eslint-disable-next-line vue/no-unused-components
    NavBar
  },
  data() {
    return {
      walletId: localStorage.getItem('walletEthId') || '',
      amount: null,
      responseMessage: ''
    };
  },
  methods: {
    async handleBuyCoins() {
      try {
        const accessToken = localStorage.getItem('accessToken');
        const headers = {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${accessToken}`
        };
        const requestData = {
          walletId: this.walletId,
          amount: this.amount
        };
        const response = await axios.post('http://localhost:9090/api/wallet/buy', requestData, { headers });
        this.responseMessage = `Buy request successful: ${response.data}`;
      } catch (error) {
        console.error('Error buying coins:', error);
        this.responseMessage = 'Error buying coins. Please try again.';
      }
    }
  }
};
</script>

<style scoped>
.buy-coins {
  display: flex;
}
.content {
  max-width: 600px;
  margin: 0 auto;
}
.form-group {
  margin-bottom: 15px;
}
.btn-primary {
  background-color: #007bff;
  color: #fff;
  border: none;
  cursor: pointer;
}
.response-message {
  margin-top: 15px;
  padding: 10px;
  background-color: #e2f0d9;
  border: 1px solid #b6d7a8;
  border-radius: 5px;
}
</style>
