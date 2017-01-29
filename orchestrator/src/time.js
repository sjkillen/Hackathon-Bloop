import "./time.scss";

import * as d3 from "d3";
import $ from "jquery";

const BAR_WIDTH = 500;

const x = d3.scaleLinear()

const svg = d3.select("svg#show");

export function getTime(){
   const range = $(".time").val();
   return +range;
}

export function update({config, showData}) {
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
   .attr("fill", "blue")
   .attr("class", "moment")
   .attr("cx", d => {
      return x(d.time)
   })
   .attr("cy", 25)
   .attr("r", 10)
   

}
