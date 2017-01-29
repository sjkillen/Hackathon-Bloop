import { update as grid } from "./grid";
import { configurator } from "./configurator";
import { Show } from "./show";
import { update as time } from "./time";
import $ from "jquery";
import {} from "./time";

const socket = io();

socket.on("connection", d => {
   const config = {
         cellDim: 100,
         cellPadd: 5,
         gridDim: 3,
         showLength: 10000,
         sounds: d.sounds
   };
   const show = new Show((moments) => {
   time({config, showData: moments});
   })
   console.log("Connected")
   configurator(
      config,
      () => {
         grid(config, show)
      }
   );
   $("#submit").on("click", e => {
      socket.emit("showtime", show.moments);
   })
});

