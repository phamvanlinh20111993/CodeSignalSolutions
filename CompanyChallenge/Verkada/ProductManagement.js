/**
 * https://app.codesignal.com/company-challenges/verkada/T5wpQMsHxSr2X3ESy
 * 
 * You work at an electronics shop, and you've just received a shipment containing new models of Verkada security cameras. With these new items, you'll need to make some updates to your product database.

Each product in your database has a unique SKU (id) and a product name (name), and the system accepts several different commands:

The methods that should be supported are listed below:

createProduct(id, name) - creates a record for a new product. Returns false if a product with the specified id already exists, and true otherwise;
updateProduct(id, name) - updates the product with the provided info. Returns false if the product with such id does not exist, and true otherwise.
deleteProduct(id) - deletes the provided product. Returns false if the product with this id does not exist, and true otherwise.
findProductById(id) - finds a product by id. Returns the product (in the form of a JSON) if the product with this id exists, and null otherwise.
findProductByTitle(title) - find a product by title. Returns the product (in the form of a JSON) if the product with this title exists, and null otherwise.
For each command of the first three types, return its result - "true" or "false" and for the last two commands return the product - its result as a stringified JSON (or "null" if no product was found).

Example

For operations = [["createProduct", "10", "Camera_10"], ["createProduct", "10", "Camera_10"], ["updateProduct", "10", "New_Camera_10"], ["deleteProduct", "9"],["findProductById", "9"], ["findProductById", "10"], ["findProductByTitle", "Camera_10"], ["findProductByTitle", "New_Camera_10"]], the output should be
solution(operations) = ["true", "false", "true", "false", "null", "{"id":"10","title":"New_Camera_10"}", "null", "{"id":"10","title":"New_Camera_10"}"].

Input/Output

[execution time limit] 4 seconds (js)

[input] array.array.string operations

An array of operations described above.

Guaranteed constraints:
1 ≤ operations.length ≤ 104,
2 ≤ operations[i].length ≤ 3.

[output] array.string

An array where the ith element is equal to the result of the ith operation.
 */


function ProductManager() {
  this.products = [];
  
  this.createProduct = function (id, title) {
    // TODO: return false if the product id already exists
    for(let i = 0; i < this.products.length; i++){
      if(this.products[i].id == id)
        return false;
    }
    
    let product = new Object();
    product.id = id;
    product.title = title;
    this.products = [...this.products, product];
    return true;
  };

  this.updateProduct = function (id, title) {
    // TODO: return false if the product doesn't exist
     var i, flag = true;
     for(let i = 0; i < this.products.length; i++){
        if(this.products[i].id == id){
          flag = false;
          this.products[i].id = id
          this.products[i].title = title
          break;
        }
     }
    
     if(flag) return false;
     return true;
  };

  this.deleteProduct = function (id) {
    // TODO: return false if the product doesn't exist
     var i, flag = true;
     for(let i = 0; i < this.products.length; i++){
        if(this.products[i].id == id){
          flag = false;
          break;
        }
     }
    
    if(flag) return false;
    
    const product = this.products.find(product => product.id === id);
    delete product;
    return true;
  };

  this.findProductById = function (id) {
    return this.products.find(product => product.id === id);
  };

  this.findProductByTitle = function (title) {
    return this.products.find(product => product.title === title);
  };
}

const productManager = new ProductManager();

function solution(operations) {
  // Calls corresponding methods of productManager based on the input
  return operations.map(operation => {
    const [methodName, ...params] = operation;
    let result = productManager[methodName].call(productManager, ...params);
    return result === undefined ? "null" : JSON.stringify(result);
  });
}
