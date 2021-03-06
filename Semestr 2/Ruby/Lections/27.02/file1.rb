# encoding: utf-8

#a=25

#puts '5*5=a'

#puts "5*5=#{a}"

=begin
puts [1,2,3].length
puts [1,2,3].size


arr = [1,2,3]
puts arr  #выводит каждый элемент
p arr	  #строку


[1,2,3].each do |e1|
	puts e1
end

[1,2,3].each {|e1| puts e1} #альтернатива верхнему. Записывается, если одна строка в теле


arr = [1,2,3]
puts arr[0]
p arr[3]

p nil.class #у него тоже есть класс 0_о

arr = [*1..100] #создает массив [a,b]
arr = [*1...100]  #создает массив [a,b)
arr = [*'A'..'Z']
p arr


arr = [1,2,3]
#добавление элемента 3 способа:
arr[3] = 4 
arr.push 4
arr << 4

p arr

arr2 = ['1', 3, arr] #неоднородные типы данных могут быть занесены 0_о

arr.shift #удаляет из начала
arr.unshift 1,2,3 # добавляет в начало
p arr,arr2

a = [] #быстрее
a = Array.new #удобнее

a = Array.new 10 #создает 10 пустых элементов 
p a


a = Array.new 10,2 #создает 10 двоек
p a

a = Array.new 10,[*1..3] #короче можно любой объект туда пихать 
p a

arr = [1,2,3]

arr = arr*3 #копирует в arr 3 раза arr
p arr

p [1,2,3].zip([3,4,5]).flatten #объединяем массив
p [1,2,3].zip([3,4,5]).flatten.sort.uniq #объединяем массив, сортирует, удаляет одинаковые 

p [1,2,3]|[3,4,5]  #объединяем массив, сортирует, удаляет одинаковые/ крутая вещь
p [1,2,3]+[3,4,5]  #объединяем массив, сортирует


arr = ['a','b','c']

p arr.map{|el| el + 'a'} #черт знает, что = бог, че хочет, то и делает в блоке
arr.map!{|el| el + 'a'} #если хочу переопределить массив arr

#...! - работает с самим объектом
#...? - возвращает bool

# var - создание переменной

#создание функции
def adding_a! chr
	chr = chr+'a'
end

str='b'
p adding_a! str


arr=[1,2,3]

#p arr.first #бозе мой, мега метод
#p arr.last # и этот

p arr.slice(1,2) #брезает массив по индексам

p arr[0..4] #вывод всех элементов

arr.slice!(0)

arr.slice!(-1) #последний элемент

arr = [1,2,3]
arr.pop(2) #обрезает суффикс 

p arr


def test? num #идут ли цифры в числе по возрастанию 
	arr=num.to_s.split //  #to_s - приведение к строке / split
	arr==arr.sort  #последняя строчка является return в методе
end

p test? 142


num=14242
p num.to_s.split(//).uniq.size #количество различных цифр в числе


num = 3

#проверка на четность
p num % 2 == 0
p (num & 1).zero? #побитовая операция


a = [1,2,3,3,3]
b = [3,4,5,5]

p (a&b).size #ищет пересечение у двух массивов

p a-b #так тоже можно делать/ это разность двух массивов



#присваивание через запятую
a,b = 1,2
p a
p b

a,b='1',[1,2]

p a
p b
=end