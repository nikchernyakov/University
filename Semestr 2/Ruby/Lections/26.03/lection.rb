=begin

# Создание массива
[*1..100]
(1..100).to_a
array = []
array = Array.new 6,0 # Аргументы: длина, значение

arr = Array.new(5){|index| index * 2} # Заполняет массив четными числами

['я','не','хочу'].max

['я','не','хочу'].max_by{|elem| elem.length} # Выбираем по длине строки

['я','нет','он'].sort_by{|elem| elem.size}.reverse

[1,2,2,3,4,4,4]-[2,4,5] # = [1,3]

[1,2,2,3,4,4,4].uniq

[1,[3,5],[4,[7,8]],9,[[14]]].flatten.max # Убирает многоуровневость

[[1,2],[3,4]].each {|elem| puts elem.max}

[[1,2],[3,4]].map {|arr| arr.max} //массив получается из элементов

[[1,2],[3,4]].transpose.map {|arr| arr.max} //транспонирует массив еще и

# Проверка на пустоту
arr.empty?
arr.size.zero?
arr == []

true = 0,1,2,3,4...
false = false,nil

!! - дает значение була
false == !!nil

[1,2,3,4].map{|elem| elem**2} # Возводит каждый элемент в 2 степень

arr.find_all{|elem| elem[0].zero?} 


[1,2,3,4,5].inject(0) {|res,el| res + el} # суммирует все в res
(первый параметр - результат, второй - итератор)

[1,2,3,4,5,6,7].partition {|el|(el%3).zero?} # Отделяет нужные элементы в отдельный массив

# Кванторы всеобщности
-   .all?
-   .any?

[1,2,3,4,5].all? {|el| el > 3}
[1,2,3,4,5].any? {|el| el > 3}

rand # Рандомайзер 
[1,2,3,4,5].sort_by{rand} # Рандомно раскидывает числа

[1,2,3,4,5].sort{|x,y| y <=> x}

# Swap
<=>
x,y = y,x

# есть два вида each
each {|el|}
each_with_index {|index,elem| }

[2,7,1]

inject(:+)


# Задача: Сравнение всех элементов массива со средним арифметическим
arr = [2,7,3]
puts arr.find_all{|elem| elem > arr.inject(:+) / arr.size}

#Задача: Найти количество максимальных элементов
arr = [1,5,3,5,5,2]
puts arr.count{|elem| elem == arr.max}

.count == .find_all.size

.select == .find_all
