/* Create new database: coffeeshopweb_db.
*/

db = db.getSiblingDB( "coffeeshopweb_db" ) ;


/* Create new collection: role.
*/

db.createCollection( "role", {

    validator: {
    
        $jsonSchema: {
        
            type: "object",
            title: "Schema for role.",
            required: [ "name" ],
            properties: {
            
                name: {
                
                    type: "string",
                    description: "name of the role.",
                    minLength: 5,
                    maxLength: 40,
                    pattern: "^ROLE_.+$"
                
                }
            
            }
        
        }
    
    }

} ) ;


/* Create new collection: account.
*/

db.createCollection( "account", {

    validator: {
    
        $jsonSchema: {
        
            type: "object",
            title: "Schema for account.",
            required: [ "username", 
                        "password", 
                        "is_account_non_expired", 
                        "is_account_non_locked", 
                        "is_credentials_non_expired", 
                        "is_enabled", 
                        "roles" ],
            properties: {
            
                username: {
                
                    type: "string",
                    description: "username of the account.",
                    minLength: 1,
                    maxLength: 40
                
                },
                password: {
                
                    type: "string",
                    description: "password of the account.",
                    minLength: 1,
                    maxLength: 100
                
                },
                is_account_non_expired: {
                
                    type: "boolean",
                    description: "is_account_non_expired of the account."
                
                },
                is_account_non_locked: {
                
                    type: "boolean",
                    description: "is_account_non_locked of the account."
                
                },
                is_credentials_non_expired: {
                
                    type: "boolean",
                    description: "is_credentials_non_expired of the account."
                
                },
                is_enabled: {
                
                    type: "boolean",
                    description: "is_enabled of the account."
                
                },
                roles: {
                
                    type: "array",
                    description: "roles of the account.",
                    items: db.getCollectionInfos( { name: "role" } )[ 0 ].options.validator.$jsonSchema
                
                }
            
            }
        
        }
    
    }

} ) ;



/* Create new collection: ingredient_option.
*/

db.createCollection( "ingredient_option", {

    validator: {
    
        $jsonSchema: {
        
            type: "object",
            title: "Schema for ingredient_option.",
            required: [ "name" ],
            properties: {
            
                name: {
                
                    type: "string",
                    description: "name of the ingredient_option.",
                    minLength: 1,
                    maxLength: 40
                
                }
            
            }
        
        }
    
    }

} ) ;



/* Create new collection: ingredient.
*/

db.createCollection( "ingredient", {

    validator: {
    
        $jsonSchema: {
        
            type: "object",
            title: "Schema for ingredient.",
            required: [ "name", 
                        "ingredient_options" ],
            properties: {
            
                name: {
                
                    type: "string",
                    description: "name of the ingredient.",
                    minLength: 1,
                    maxLength: 40
                
                },
                ingredient_options: {
                
                    type: "array",
                    description: "ingredient_options of the ingredient.",
                    items: db.getCollectionInfos( { name: "ingredient_option" } )[ 0 ].options.validator.$jsonSchema
                
                }
            
            }
        
        }
    
    }

} ) ;



/* Create new collection: coffee_order.
*/

db.createCollection( "coffee_order", {

    validator: {
    
        $jsonSchema: {
        
            type: "object",
            title: "Schema for coffee_order.",
            required: [ "order_number", 
                        "ordered_by_username", 
                        "selected_ingredients", 
                        "selected_ingredient_options" ],
            properties: {
            
                order_number: {
                
                    type: "number",
                    description: "order_number of the coffee_order.",
                    minimum: 1
                
                },
                ordered_by_username: {
                
                    type: "string",
                    description: "ordered_by_username of the coffee_order.",
                    minLength: 1,
                    maxLength: 40
                
                },
                selected_ingredients: {
                
                    type: "array",
                    description: "selected_ingredients of the coffee_order.",
                    items: db.getCollectionInfos( { name: "ingredient" } )[ 0 ].options.validator.$jsonSchema
                
                },
                selected_ingredient_options: {
                
                    type: "array",
                    description: "selected_ingredient_options of the coffee_order.",
                    items: db.getCollectionInfos( { name: "ingredient_option" } )[ 0 ].options.validator.$jsonSchema
                
                }
            
            }
        
        }
    
    }

} ) ;






