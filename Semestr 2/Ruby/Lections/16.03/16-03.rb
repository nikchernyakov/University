=begin
Выкладываем все в gist.github
Задание брать из itmoprog/2016s2ruby
=end

=begin

Стиль руби:

-два пробела вместо таба

-одна пустая строка после кода

-если используем не только латинские буквы, пишем: 
# encoding: utf-8

-названия переменых должны быть понятны

-одинарные кавычки используются везде, где не происходят вычисления

-стремное изменение типов данных

-метод должен в руби иметь не больше 10 строк

-переменные, методы с нижним почеркиванием

-нельзя(не рекомендуется) использовать for

-do у while ставить не надо

- while - until
  if - unless

{key: 'value'} - хэши записываются так
{:key => 'value'} - раньше писали так



Задача: 
будет выложена потом

пометки: логины нужно переписать с русского на английский login 
-обработка одинаковых фамилий и первых букв имении
-если прям все совпадает нужно добавить номер
с помощью DATA.read можно читать все после __END__
-записывать все логины в массив logins



local_variables - вывод в виде массива всех переменных

Ruby Style Guide - помощник по его стилю

Gem"ы для транслита:
-translit
-russian

gem install - установка

require translit - в самом коде
