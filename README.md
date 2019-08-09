<h1 align="center">
  사전과제 2. 생태 정보 서비스 API 개발
</h1>

대한민국 지역기반 생태 관광 정보 서비스를 개발하려고 합니다. 한국 관광공사가 보유하고 있 는 생태 관광 정보를 지역별로 제목, 개요, 연락처 등의 정보를 활용하여 아래 API 기능 명세와 제약 사항에 맞게 개발하세요.

## 개발 프레임워크 및 기술

* Spring Boot
* jjwt – 0.9.0
* Spring Security
* Spring JPA
* Lombok
* MySQL

## 문제해결 전략

JWT (JSON Web Token)는 당사자간에 정보를 안전하게 JSON 객체로 전송하기위한 간결하고 독립적 인 방법을 정의합니다.

    .
    └── api
         ├── auth
              ├── signup
              └── signin
         ├── admin
              ├── ping
              └── insertRegion
              └── updateRegion
              └── insertProgram
              └── updateProgram
         └── touristInfo
              ├── frequency
              └── retrieve
              └── recommend

1.  **`/api/auth/signup`**: 회원가입 API. 예시)
<pre>
{
	"name": "Admin",
	"username": "Kyle",
	"email" : "kyle@master.com",
	"role" : ["admin"],
	"password" : "admin1234!"
}
</pre>

1.  **`/src`**: This directory will contain all of the code related to what you will see on the front-end of your site (what you see in the browser) such as your site header or a page template. `src` is a convention for “source code”.

2.  **`.gitignore`**: This file tells git which files it should not track / not maintain a version history for.

3.  **`.prettierrc`**: This is a configuration file for [Prettier](https://prettier.io/). Prettier is a tool to help keep the formatting of your code consistent.

4.  **`gatsby-browser.js`**: This file is where Gatsby expects to find any usage of the [Gatsby browser APIs](https://www.gatsbyjs.org/docs/browser-apis/) (if any). These allow customization/extension of default Gatsby settings affecting the browser.

5.  **`gatsby-config.js`**: This is the main configuration file for a Gatsby site. This is where you can specify information about your site (metadata) like the site title and description, which Gatsby plugins you’d like to include, etc. (Check out the [config docs](https://www.gatsbyjs.org/docs/gatsby-config/) for more detail).

6.  **`gatsby-node.js`**: This file is where Gatsby expects to find any usage of the [Gatsby Node APIs](https://www.gatsbyjs.org/docs/node-apis/) (if any). These allow customization/extension of default Gatsby settings affecting pieces of the site build process.

7.  **`gatsby-ssr.js`**: This file is where Gatsby expects to find any usage of the [Gatsby server-side rendering APIs](https://www.gatsbyjs.org/docs/ssr-apis/) (if any). These allow customization of default Gatsby settings affecting server-side rendering.

8.  **`LICENSE`**: Gatsby is licensed under the MIT license.

9.  **`package-lock.json`** (See `package.json` below, first). This is an automatically generated file based on the exact versions of your npm dependencies that were installed for your project. **(You won’t change this file directly).**

10. **`package.json`**: A manifest file for Node.js projects, which includes things like metadata (the project’s name, author, etc). This manifest is how npm knows which packages to install for your project.

11. **`README.md`**: A text file containing useful reference information about your project.

## 빌드 및 실행 방법

[![Deploy to Netlify](https://www.netlify.com/img/deploy/button.svg)](https://app.netlify.com/start/deploy?repository=https://github.com/gatsbyjs/gatsby-starter-blog)
