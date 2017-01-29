import "./time.scss";

import * as d3 from "d3";
import $ from "jquery";

const BAR_WIDTH = 500;

const x = d3.scaleLinear()
const legend = d3.scaleOrdinal(d3.schemeCategory10)

const svg = d3.select("svg#show");

export function getTime(){
   const range = $(".time").val();
   return +range;
}

$(() => {
      function change() {
            $("#select-time").text(`Play sound at ${$(".time").val()/1000} Seconds`);
      }
      $(".time").on("change", change);
      $(".time").on("input", change);
      change();
});
export function update({config, showData}) {
      legend.domain(config.sounds)
      x.domain([0, config.showLength])
      .range([0, BAR_WIDTH]);

   const join = svg
   .selectAll(".moment")
   .data(showData, d => {
      d ? x(d.time) : this.x;
   });

   join.exit()
      .remove()

   join.enter()
   .append("circle")
   .attr("fill", d => legend(d.sound))
   .attr("class", "moment")
   .attr("cx", d => {
      return x(d.time)
   })
   .attr("cy", 25)
   .attr("r", 10)
   

}
