const User = require('../entities').User;
let sequelize = require('../entities').sequelize;
let Guard = require('../models/Guard');
let Customer = require('./CustomersRepository');
let Shopkeeper = require('./ShopkeepersRepository');
let RestaurantAdmin = require('./RestaurantAdminsRepository');

module.exports.findByEmail = async function(email) {
  return await User.findOne({
    where: { email: email }
  });
};

let create = function(userData, t) {
  let credentials = Guard.generateCredentials(userData.password);
  return User.create(
    {
      email: userData.email,
      hash: credentials.hash,
      type: userData.type,
      id_number: userData.id_number,
      name: userData.name,
      phone: userData.phone,
      salt: credentials.salt,
      last_interaction_date: Date.now()
    },
    { transaction: t }
  );
};

module.exports.register = function(userData) {
  if (userData.type == 'customer') {
    return Customer.create(userData);
  } else if (userData.type == 'shopkeeper') {
    return Shopkeeper.create(userData);
  } else if (userData.type == 'restaurant_admin') {
    return RestaurantAdmin.create(userData);
  } else {
    return sequelize.transaction(t => create(userData, t));
  }
};

module.exports.create = create;
