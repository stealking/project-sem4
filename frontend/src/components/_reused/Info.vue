<template lang="pug">
doctype html
._parent(@click='_onclick').sd.m1.removeYPaddingYMarginOnVeryNarrow
  .child(:style="{ backgroundImage: 'url(' + image + ')' }")
    a.label.p1
      .text.s6.tsd 3 ngay 2 dem
      .text.s5.tsd Từ 3.000.000 vnd
    .fixLabel.p1.pt3
      .text.s8.bold.tsd Đà nẵng - đồng nai - hồ chí minh
</template>
<script>

export default {
  props: ['onclick', 'image'],
  methods: {
    _onclick() {
      this.$emit('onclick');
    }
  }
};
</script>

<style scoped lang="stylus">

.label
  width 100%
  opacity: 0;
  font-size: 35px;
  color: #ffffff !important;
  font-family: sans-serif;
  text-align: center;
  margin: auto;
  position: absolute;
  transform: translate(-50%,5%)
  left: 50%;
  top: 50%;
  cursor: pointer;

  /*text-decoration: none;*/
  transition: all .5s;
.fixLabel
  opacity 1
  position: absolute;
  transition: all .5s;
  color: #fff;
  background-color: rgba(255,255,255,0.1);

._parent
  width: 320px;
  height: 220px;
  overflow: hidden;
  position: relative;
  float: left;
  display: inline-block;
  cursor: pointer;

.child
  height: 100%;
  width: 100%;
  background-size: cover;
  background-repeat: no-repeat;
  transition: all .5s;


._parent:hover .child, ._parent:focus .child
  transform: scale(1.25);


._parent:hover .child:before, ._parent:focus .child:before
  display: block;


._parent:hover a, ._parent:focus a
  opacity 1
  transform translate(-50%,-15%) scale(.8)

._parent:hover .fixLabel, ._parent:focus .fixLabel
  transform scale(.8)
  color: #fff;
  background-color: rgba(0,0,0,0);
.child:before
  content: "";
  display: none;
  height: 100%;
  width: 100%;
  position: absolute;
  top: 0;
  left: 0;
  background-color: rgba(52,73,94,0.75);

</style>
