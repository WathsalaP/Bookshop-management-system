import { defineConfig } from 'vite'
import { resolve } from 'path'

export default defineConfig({
  root: 'frontend', // Set root to frontend folder
  build: {
    outDir: '../dist', // Build output to parent directory
    rollupOptions: {
      input: {
        main: resolve(__dirname, 'frontend/login.html')
      }
    }
  },
  server: {
    open: '/login.html' // Opens from the frontend root
  }
})