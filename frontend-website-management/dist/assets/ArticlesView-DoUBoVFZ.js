import{a as v}from"./api-F9C-BKG7.js";import{u as x,k as z,b as B,c as l,d as u,e,f as h,w as _,i as C,t as g,l as V,r as d,o as q,m as F,q as N,F as $,h as E,s as O,v as R}from"./index-CMPOgCmU.js";import{_ as A}from"./_plugin-vue_export-helper-DlAUqK2U.js";import{P as S}from"./Page-CCejXslC.js";import"./axios-Bo0ATomq.js";const T={class:"article-item card p-0"},M={class:"row"},U={class:"col-md-12"},G={class:"img-container"},L=["src"],W={class:"col-md-12"},j={class:"card-body"},H={class:"date-category"},J={class:"article-title",style:{"font-size":"xx-large"}},K={style:{"max-height":"15vh"},class:"card-text content article-content"},Q={__name:"ArticleItem",props:["article"],setup(s){x();const o=s,a="https://xiaoqingblog-xiaoqingblog.azuremicroservices.io/xiaoqingblog/images",c=z(()=>`${a}${o.article.articleThumbnailImagePath}`);return(i,n)=>{const r=B("router-link");return l(),u("div",T,[e("div",M,[e("div",U,[h(r,{to:`/home/articlesDetails/${s.article.articleId}`,class:"custom-link"},{default:_(()=>[e("div",G,[c.value?(l(),u("img",{key:0,src:c.value,class:"img-fluid",alt:"Article Image"},null,8,L)):C("",!0)])]),_:1},8,["to"])]),e("div",W,[e("div",j,[e("div",H,[e("p",null,g(s.article.articleDate),1),h(r,{to:`/home/articles/classes/${s.article.articleClass.articleClassId}`,class:"custom-link"},{default:_(()=>[e("p",null,g(s.article.articleClass.articleClassName),1)]),_:1},8,["to"])]),h(r,{to:`/home/articlesDetails/${s.article.articleId}`,class:"custom-link"},{default:_(()=>[e("p",J,g(s.article.articleTitle),1)]),_:1},8,["to"]),e("p",K,g(s.article.articleSummaryContent),1)])])])])}}},X=A(Q,[["__scopeId","data-v-01dccead"]]),Y={class:"article-item-container"},Z={class:"row"},k="/v1/articles",ee="/v1/articleclasses",I=9,te={__name:"ArticlesView",setup(s){const o=x(),a=V(),c=d(1),i=d(0),n=d([]),r=d([]),f=d(!1);q(()=>{D()});const p=async()=>{console.log("getArticles");const t=await v(`${k}?page=${c.value-1}&size=${I}`,o);n.value=t.data.content,i.value=t.data.totalPages,console.log(n.value),w()},P=async()=>{const t=a.params.id,m=await v(`${k}/class/${t}?page=${c.value-1}&size=${I}`,o);n.value=m.data.content,i.value=m.data.totalPages,w()},b=async t=>{t>=1&&t<=i.value&&(c.value=t,a.name==="home.articles"?await p():a.name==="home.articles.classes.id"&&await P())},w=()=>{R(()=>{f.value=!0})},D=async()=>{const t=await v(ee,o);r.value=t.data};return F(()=>{console.log("watchEffect"),console.log(a.name),a.name==="home.articles"?p():(a.name==="home.articles.classes.id"||a.name==="home.articlesDetails")&&(console.log("test"),P())}),N(()=>a.params.id,(t,m)=>{c.value=1}),(t,m)=>(l(),u($,null,[e("div",Y,[e("div",Z,[(l(!0),u($,null,E(n.value,y=>(l(),u("div",{class:"col-md-4",key:y.id},[e("div",null,[h(X,{article:y},null,8,["article"])])]))),128))])]),f.value?(l(),O(S,{key:0,currentPage:c.value,totalPages:i.value,changePage:b},null,8,["currentPage","totalPages"])):C("",!0)],64))}},ie=A(te,[["__scopeId","data-v-a6a4b100"]]);export{ie as default};
