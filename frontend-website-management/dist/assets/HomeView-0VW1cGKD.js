import{a as f}from"./api-F9C-BKG7.js";import{r as m,u as w,o as k,a as x,b as u,c as t,d as o,e,f as a,w as r,g as i,n as h,F as N,h as C,t as V,i as $,p as y,j as I}from"./index-CMPOgCmU.js";import{_ as g}from"./_plugin-vue_export-helper-DlAUqK2U.js";import"./axios-Bo0ATomq.js";const S={class:"navbar navbar-expand-lg navbar-light bg-light sticky-top"},F={class:"container"},B=e("button",{class:"navbar-toggler",type:"button","data-bs-toggle":"collapse","data-bs-target":"#navbarNav","aria-controls":"navbarNav","aria-expanded":"false","aria-label":"Toggle navigation"},[e("span",{class:"navbar-toggler-icon"})],-1),L={class:"collapse navbar-collapse",id:"navbarNav"},j={class:"navbar-nav d-flex justify-content-between w-100"},D={class:"nav-item"},E={key:0,class:"nav-item ml-auto"},H={class:"nav-item dropdown"},M=e("a",{class:"nav-link dropdown-toggle",href:"#",role:"button","data-bs-toggle":"dropdown","aria-haspopup":"true","aria-expanded":"false"}," 文章分類 ",-1),R={class:"dropdown-menu"},T={class:"nav-item"},A="/v1/articleclasses",G={__name:"Navbar",setup(s){const d=m(!1),l=w(),n=m([]),v=()=>{d.value=window.scrollY>0},b=async()=>{const c=await f(A,l);n.value=c.data};return k(()=>{window.addEventListener("scroll",v),b()}),x(()=>{window.removeEventListener("scroll",v)}),(c,J)=>{const _=u("router-link");return t(),o("nav",S,[e("div",F,[B,e("div",L,[e("ul",j,[e("li",D,[a(_,{to:"/home/articles",class:h(["nav-link",{active:c.$route.path==="/home/articles"}])},{default:r(()=>[i("回首頁")]),_:1},8,["class"])]),n.value.length>0?(t(),o("li",E,[e("div",H,[M,e("ul",R,[(t(!0),o(N,null,C(n.value,p=>(t(),o("li",{key:p.articleClassId},[a(_,{to:`/home/articles/classes/${p.articleClassId}`,class:"dropdown-item"},{default:r(()=>[i(V(p.articleClassName),1)]),_:2},1032,["to"])]))),128))])])])):$("",!0),e("li",T,[a(_,{to:"/home/aboutMe",class:h(["nav-link",{active:c.$route.path==="/home/aboutMe"}])},{default:r(()=>[i("關於我")]),_:1},8,["class"])])])])])])}}},Q="/assets/homeScape-CbOH7pGW.jpg",U={},X=s=>(y("data-v-9ba56793"),s=s(),I(),s),z={class:"desktop"},O={class:"logo-container"},P=X(()=>e("img",{src:Q,alt:"XiaoQingBlog Logo",class:"logo-image"},null,-1));function W(s,d){const l=u("router-link");return t(),o("div",z,[e("div",O,[a(l,{to:"/home/articles",class:"logo-text custom-link"},{default:r(()=>[i("XiaoQing")]),_:1}),P])])}const Y=g(U,[["render",W],["__scopeId","data-v-9ba56793"]]),q={__name:"HomeView",setup(s){return(d,l)=>{const n=u("RouterView");return t(),o("div",null,[a(Y),a(G),a(n)])}}},se=g(q,[["__scopeId","data-v-e75f7960"]]);export{se as default};