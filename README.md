---

📘 ConvoAI Admin Console

🚀 A powerful Android Admin Panel to remotely control, manage, and monetize your AI-powered app in real-time using Firebase.


---

🔥 Overview

ConvoAI Admin Console is a remote control center designed to manage your ConvoAI User App without publishing updates.

With this app, you can:

👥 Manage users (ban, reward, monitor)

💰 Control app economy (coins, pricing, rewards)

🤖 Update AI behavior instantly

📢 Send push notifications

📊 Monitor analytics in real-time

⚙️ Change settings without app updates


> ⚡ All changes are applied instantly using Firebase (Realtime Sync)




---

🏗️ Architecture

Admin App (Android)
        ↓
Firebase (Auth + Firestore + Remote Config + FCM)
        ↓
User App (Receives updates instantly)

Tech Stack

🟣 Kotlin (MVVM Architecture)

🎨 Material 3 UI

🔥 Firebase:

Authentication

Firestore Database

Remote Config

Cloud Messaging (FCM)

Analytics (Optional)




---

📱 Features

🔐 Admin Authentication

Secure Firebase login

Admin email whitelist

Optional biometric login

Session management



---

📊 Dashboard

Total users

Active users

Coins circulation

Maintenance mode status

App version tracking



---

👥 User Management

Search users by email

View user stats

Ban / Unban users

Gift coins (100 / 500 / 1000)

Reset coins

Delete users



---

⚙️ Configuration Center

Update API keys

Change AI model

Modify system prompt

Adjust AI parameters (temperature, tokens)

Toggle maintenance mode



---

💰 Economy Control

Cost per message

Cost per image

Ad reward coins

Initial coins

Daily bonus / referral bonus



---

📢 Notification Center

Send broadcast notifications

Target specific users

Schedule messages

Store notification history



---

📈 Analytics

Daily active users

Coins usage

Revenue estimation

Growth trends



---

📝 Admin Logs

Track admin actions:

Coin gifting

User bans

Config updates

Notifications sent




---

📂 Project Structure

com.duddleTech.convoAI.admin

├── core/
│   ├── AdminApp.kt
│   ├── FirebaseManager.kt
│   ├── SessionManager.kt
│
├── auth/
│   └── LoginActivity.kt
│
├── dashboard/
│   └── MainActivity.kt
│
├── users/
│   ├── UserListActivity.kt
│   ├── UserDetailActivity.kt
│   ├── UserAdapter.kt
│   └── UserModel.kt
│
├── config/
│   └── ConfigActivity.kt
│
├── economy/
│   └── EconomyActivity.kt
│
├── notifications/
│   └── NotificationActivity.kt
│
├── analytics/
│   └── AnalyticsActivity.kt
│
├── logs/
│   └── LogsActivity.kt


---

⚙️ Installation

1️⃣ Clone the Repository

git clone https://github.com/your-username/convoai-admin-console.git


---

2️⃣ Open in Android Studio

Open project

Sync Gradle



---

3️⃣ Connect Firebase

Go to Firebase Console

Create project

Add Android app

Download google-services.json

Place inside:


app/


---

4️⃣ Enable Firebase Services

Authentication → Email/Password

Firestore Database

Remote Config

Cloud Messaging



---

5️⃣ Run the App

Run ▶️


---

🔐 Security Setup

Admin Email Whitelist

Update inside:

LoginActivity.kt

val adminEmails = listOf("admin@gmail.com")


---

Firestore Rules (Example)

rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {

    match /{document=**} {
      allow read, write: if request.auth.token.email == "admin@gmail.com";
    }
  }
}


---

🗄️ Firestore Structure

users/
   uid/
      email
      coins
      isBanned
      createdAt

admin_logs/
   logId/
      action
      userId
      amount
      timestamp

config/
   app_settings/
      apiKey
      model
      systemPrompt
      maintenanceMode

economy/
   pricing/
      messageCost
      imageCost
      rewardCoins


---

🚀 Deployment

Build APK / AAB

Install on Admin device

Connect to same Firebase as User App



---

💡 Use Cases

AI Chat Apps

SaaS AI tools

Subscription-based apps

Coin-based monetization apps

Content generation apps



---

🛡️ Best Practices

Use Proguard for release builds

Never expose API keys in app code

Use Remote Config for dynamic updates

Monitor logs regularly

Enable Firebase App Check (recommended)



---

📸 Screenshots (Add Later)

Login Screen

Dashboard

User Manager

Config Panel

Analytics



---

🤝 Contribution

Pull requests are welcome. For major changes, open an issue first.


---

📜 License

This project is licensed under the MIT License.


---

💼 Author

DuddleTech


---

⭐ Support

If you like this project:

⭐ Star this repo
💬 Share feedback
🚀 Use it in your apps


---

🔮 Future Improvements

Multi-admin roles

Web admin panel

Advanced analytics

Subscription integration

AI usage tracking



---
