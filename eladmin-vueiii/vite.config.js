import { resolve } from "path";
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
    base: "./",
    plugins: [vue()],
    resolve: {
        alias: {
            '@': resolve(__dirname, "./src"),
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
        port: 8087,
        open: false
    }
})
