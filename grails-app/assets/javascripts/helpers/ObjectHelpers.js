function ObjectHelpers() {
    var getRemoveEmptyPropertiesFromObjectReducer = function(originalObject) {
        return function(accumulator, propertyKey) {
            return removeEmptyPropertiesFromObjectReducer(accumulator, propertyKey, originalObject);
        };
    };

    var removeEmptyPropertiesFromObjectReducer = function(accumulator, propertyKey, originalObject) {
        if (originalObject[propertyKey] !== "" && typeof originalObject[propertyKey] !== "undefined") {
            var newFormValueObject = {};
            newFormValueObject[propertyKey] = originalObject[propertyKey];

            return Object.assign(accumulator, newFormValueObject);
        }

        return accumulator;
    };

    this.removeEmptyProperties = function(originalObject) {
        var reducer = getRemoveEmptyPropertiesFromObjectReducer(originalObject);

        return Object.keys(originalObject).reduce(reducer, {});
    };
}

var objectHelpers = new ObjectHelpers();
