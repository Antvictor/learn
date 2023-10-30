const workers = new Worker("/scripts/gen.js");

document.querySelector("#wake-up").addEventListener("click", () => {
    document.querySelector("#output").textContent = `3秒内给我起床!!`;
    workers.postMessage({
        command: "gen",
        time: 1_000_000
    });
})


workers.addEventListener("message", (message) => {
    document.querySelector("#output").textContent = `我起来了，只用了${message.data}秒`;
})