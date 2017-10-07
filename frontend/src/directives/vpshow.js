export default {
  inViewport(el) {
    const rect = el.getBoundingClientRect();
    return !(rect.bottom < 0 || rect.right < 0 ||
      rect.left > window.innerWidth ||
      rect.top > window.innerHeight);
  },
  /*eslint-disable */
  bind(el, binding) {
    el.classList.add('before-enter');
    el.$onScroll = function () {
      if (binding.def.inViewport(el)) {
        el.classList.add('enter');
        el.classList.remove('before-enter');
        binding.def.unbind(el, binding);
      }
    };
    document.body.addEventListener('scroll', el.$onScroll);
  },

  inserted(el, binding) {
    el.$onScroll();
  },

  unbind(el, binding) {
    document.body.removeEventListener('scroll', el.$onScroll);
    delete el.$onScroll;
  },
}
