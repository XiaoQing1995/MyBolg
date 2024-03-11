<template>
  <div class="about-me">
    <div class="profile">
      <img src="@/assets/img/me4.jpg" alt="Profile Image" class="profile-image" />
      <h1 class="name">陳慶君</h1>
      <p class="bio">一個正在努力成為工程師的小菜鳥.</p>
    </div>
    <div class="contact">
      <h2>Contact Information</h2>
      <ul>
        <li>
          Email:
          <a href="mailto:xiaoqingzi718@gmail.com" class="link"
            >xiaoqingzi718@gmail.com</a
          >
        </li>
        <li>
          GitHub:
          <a href="https://github.com/XiaoQing1995" target="_blank" class="link"
            >github.com/XiaoQing1995</a
          >
        </li>
      </ul>
    </div>
    <div class="skills">
      <h2>Skills</h2>
      <ul>
        <li>Java</li>
        <li>Vue.js</li>
        <li>MS SQL</li>
        <li>Web Development</li>
      </ul>
    </div>
  </div>
</template>

<style scoped>
.about-me {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  font-family: "Arial", sans-serif;
}

.profile {
  text-align: center;
}

.profile-image {
  width: 20vh;
  height: 20vh;
  border-radius: 50%;
  margin-bottom: 10px;
}

.name {
  font-size: 2rem;
  margin-bottom: 10px;
}

.bio {
  font-size: 1.2rem;
  color: #555;
}

.contact,
.skills {
  margin-top: 20px;
}

h2 {
  font-size: 1.5rem;
  margin-bottom: 10px;
}

ul {
  list-style-type: none;
  padding: 0;
}

.link {
  color: #007bff; /* 超連結顏色 */
  text-decoration: none;
}

.link:hover {
  text-decoration: underline; /* 滑鼠懸停時加底線 */
}
</style>
