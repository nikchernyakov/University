# Задача 1
a = [1,2,3,4,5]
puts a.map {|e| e += e.even? ? a.first : a.last } 

# Задача 2
a = [1,2,5,8,6,3]
p a.partition {|elem| a.index(elem).even?}.flatten

# Задача 3
a = [1,-2,5,8,-6,3]
p a.map {|e| e > 0 ? a.min : e }

# Задача 4
a = [1,2,2,3,4,4,5,5,6,7,7]
puts a.inject(Hash.new{0}) {|h,e| h[e] += 1; h}

# Методы практически такие же, как в array
# Такие методы как sort, преобразует хэш в массив

# Преоразование массива в хэш
Hash[*array]
# Ну понятно
h.keys
h.values

# Задача 5
a = [[1,3,2],[4,5,6],[1,9,4],[9,14,18]]
p a.map {|e| e == e.sort ? e.sort_by{rand} : e.sort}