
# Restaurant Management System 🍽️

A complete desktop application for restaurant operations management built with **Java Swing** and **MySQL**. Streamline order taking, billing, inventory, and customer management with an intuitive interface.

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-Desktop_App-blue)

## ✨ Features

- **👤 Role-based Login** - Admin, Manager, and Staff access levels
- **📋 Menu Management** - Add, edit, delete food items with categories
- **🛒 Order Processing** - Quick order taking with table management
- **💰 Billing System** - Generate bills with tax calculation
- **📊 Inventory Tracking** - Monitor stock levels in real-time
- **👥 Customer Database** - Store customer information and order history
- **📈 Reports Dashboard** - Sales analytics and performance reports
- **🖨️ Receipt Printing** - Print bills and kitchen orders

## 🚀 Quick Start

### Prerequisites
- Java JDK 8 or higher
- MySQL Server 5.7+
- MySQL Connector/J driver

### Installation
1. **Clone the repository**
   ```bash
   git clone https://github.com/asheesh109/Restaurant-Management-System.git
   ```

2. **Set up the database**
   - Import `database/restaurant_schema.sql` to MySQL
   - Update database credentials in `config.properties`

3. **Run the application**
   ```bash
   cd Restaurant-Management-System
   java -jar RestaurantSystem.jar
   ```

## 🔧 Configuration

Edit `config.properties`:
```properties
db.url=jdbc:mysql://localhost:3306/restaurant_db
db.username=root
db.password=yourpassword
restaurant.name=Your Restaurant
tax.rate=18.0
```

## 📦 Dependencies
- MySQL Connector/J
- JasperReports (for reports)
- iText PDF (for receipts)

## 🤝 Contributing
Contributions welcome! Please fork the repository and submit pull requests.

## 📞 Support
For issues or questions:
1. Check the existing issues
2. Create a new issue with details
3. Email: ashishparab03@gmail.com

## 📜 License
MIT License - see LICENSE file for details.

---
**Developed with ❤️ for restaurant owners everywhere**
```
