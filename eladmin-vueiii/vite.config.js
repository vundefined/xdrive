import {resolve} from "path";
import { fileURLToPath, URL } from 'node:url'
import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

// https://vitejs.dev/config/
export default defineConfig({
  base: "./",
  plugins: [
    vue(),
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [ElementPlusResolver()],
    }),
  ],
  resolve: {
    alias: {
      // '@': resolve(__dirname, "./src"),
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  build: {
    outDir: "dist",
    terserOptions: {
      compress: {
        keep_infinity: true,  // 防止 Infinity 被压缩成 1/0，这可能会导致 Chrome 上的性能问题
        drop_console: true,	// 生产环境去除 console
        drop_debugger: true	// 生产环境去除 debugger
      },
    },
    chunkSizeWarningLimit: 1500	// chunk 大小警告的限制（以 kbs 为单位）
  },
  server: {
    open: true,
    port: 8087,
  }
})
