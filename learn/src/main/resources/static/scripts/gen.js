addEventListener("message", (message) => {
    if (message.data.command === "gen") {
           setTimeout(() => {
               postMessage(message.data.time);
           }, 2000);
       }
});