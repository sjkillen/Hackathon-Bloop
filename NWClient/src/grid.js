import "./grid.scss";

import * as d3 from "d3";
import * as $ from "jquery";


   const svg = d3.select("svg#drawing");

export function update(config, show) {
   const gridData = [];
   const { cellDim, cellPadd, gridDim } = config
   for (let x = 0; x < gridDim; x++) {
      for (let y = 0; y < gridDim; y++) {
         gridData[x * gridDim + y] = {
            active: false,
            x, y
         }
      }
   }


   const join = svg
   .attr("width", window.innerWidth + cellDim * gridDim)
   .attr("height", window.innerHeight + cellDim * gridDim)
      .selectAll(".cell")
      .data(gridData, d => {
         d = d ? d : this;
         if (!d) return
         return d.x + "," + d.y;
      });


   join.exit()
      .remove()

   join.enter()
   .append("rect")
      .attr("fill", "red")
      .attr("width", cellDim - cellPadd * 2)
      .attr("height", cellDim - cellPadd * 2)
      .attr("class", "cell")
      .attr("x", d => d.x * cellDim + cellPadd)
      .attr("y", d => d.y * cellDim + cellPadd)
   .on("click", ({x, y}) => {
      show.addMoment({x, y, sound: config.sound})
   })
   

}