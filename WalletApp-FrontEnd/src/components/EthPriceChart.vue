<!-- src/components/EthPriceChart.vue -->
<template>
  <div>
    <canvas id="ethPriceChart"></canvas>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import Chart from 'chart.js/auto';
import axios from 'axios';
import 'chartjs-adapter-date-fns';

export default {
  name: 'EthPriceChart',
  setup() {
    const chart = ref(null);

    const fetchEthPriceData = async () => {
      try {
        const response = await axios.get('https://api.coingecko.com/api/v3/coins/ethereum/market_chart', {
          params: {
            vs_currency: 'usd',
            days: '30', // Get data for the last 30 days
          },
        });
        const data = response.data.prices.map(price => ({
          x: new Date(price[0]),
          y: price[1],
        }));

        const ctx = document.getElementById('ethPriceChart').getContext('2d');
        chart.value = new Chart(ctx, {
          type: 'line',
          data: {
            datasets: [{
              label: 'ETH Price (USD)',
              data: data,
              fill: false,
              borderColor: 'rgba(75, 192, 192, 1)',
              tension: 0.1,
            }],
          },
          options: {
            scales: {
              x: {
                type: 'time',
                time: {
                  unit: 'day',
                },
              },
            },
          },
        });
      } catch (error) {
        console.error('Error fetching ETH price data:', error);
      }
    };

    onMounted(() => {
      fetchEthPriceData();
    });

    return {
      chart,
    };
  },
};
</script>

<style scoped>
canvas {
  max-width: 100%;
  height: auto;
}
</style>
