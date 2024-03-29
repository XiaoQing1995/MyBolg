# XiaoQingBlog

## 目標與動機
製作這個網站的主要目標是提高自己的學習效率，通過記錄學習內容來加深對知識的理解和掌握。此外，這個網站也是我練習使用相關技術的機會，包括前端開發、後端開發、數據庫管理等等。

## 功能概述
- 學習記錄：我可以添加、編輯和刪除學習記錄，包括學習的主題、內容、日期等信息。
- 分類功能：可以透過分類瀏覽該分類的所有學習紀錄。
- 帳戶管理：主要分為 USER 及 ADMIN 兩種帳戶，User 主要負責文章的相關功能，而 ADMIN 除了文章的相關功能還可以修改帳戶的資料，包括修改密碼、用戶權限。
- 身份認證：透過 Spring Security 及 JWT 實作身份認證及授權機制。

## 使用技術
### 前端技術
- **Vue.js**: 流行的 JavaScript 框架，用於構建用戶界面。
- **HTML/CSS**: 用於結構化和樣式化網頁內容。
- **JavaScript**: 用於實現網頁交互和動態效果。
- **Bootstrap**: 用於設計和開發響應式網頁。
- **Swal**: 自定義警告框的函式庫，用於替代傳統的 JavaScript alert 對話框，讓彈出提示更加直觀和美觀。
- **TinyMCE**: 富文本編輯器，讓使用者能夠以所見即所得（WYSIWYG）的方式編輯 HTML 內容。

### 後端技術
- **Spring Boot**: 一個用於快速開發 Spring 應用程序的框架。
- **Java**: 主要的後端開發語言，用於編寫後端邏輯和業務邏輯。
- **Spring Data JPA**: 用於數據持久化和訪問關聯式數據庫的 Spring 模塊。
- **Spring Security**: 提供身份驗證和授權功能，用於保護應用程序的安全性。

### 資料庫
- **Azure SQL**: 基於 Microsoft SQL Server 技術，但提供了作為雲服務的額外特性和優勢。

### 其它技術
- **Git**: 用於版本控制和代碼管理。
- **Maven**: 自動化構建過程，包括依賴管理、打包和測試。
- **RESTful API**: 提供清晰、可擴展和易於維護的 Web 服務接口。
- **JWT（JSON Web Token）**: 身份認證和授權機制，保護應用免受未授權訪問。

## 雲端部署與服務
- **Azure 靜態網頁應用程式**：前端部分部署在 Azure 靜態網頁應用程式（Azure Static Web Apps）服務上，這是一種用於托管靜態網站的服務，提供自動化的構建和部署流程，並內建了 CI/CD 支持。
- **Azure SQL Server**：使用 Azure SQL Server 來存儲和管理應用數據。這是一種關聯式資料庫服務，提供了 SQL Server 的功能和特性，同時也獲得了雲計算的好處，如可擴展性、可用性和安全性。
- **Azure Spring Apps**：後端應用程式採用 Spring Boot 開發，並部署在 Azure Spring Apps 服務上。這是一個完全管理的 Spring Cloud 環境，支持 Java 微服務架構的快速開發和部署。
- **Azure Blob Storage**：用於存儲和管理應用程式的非結構化數據（如圖片、文件等）。Azure Blob Storage 提供了高度可擴展的對象存儲解決方案，並支持全球訪問和大數據工作負載。


## 本地端佈署方式
- **後端部分**：在 src/main/resource 新增 application.properties 檔案，並將下圖相關屬性定義好，再到 Controller 將 @CrossOrigin 改為可訪問的網域即可，
![image](https://github.com/XiaoQing1995/MyBolg/assets/133472373/f54728f2-d7cf-49d7-b797-29f95c046432)

- **前端部分**：在前端的最上層資料夾新增 .env 檔案，並將下圖相關屬性定義好即可
  ![image](https://github.com/XiaoQing1995/MyBolg/assets/133472373/73b350a1-5018-4463-af0e-796aa1a819c9)

- **資料庫部分**：可直接使用 MyBlog.bacpac 匯入資料庫

