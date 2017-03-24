# encoding: UTF-8

require 'translit'

logins = []
names = []

DATA.each_line.map(&:chomp).each { |name| \
  names << Translit.convert(name, :english).downcase }

names.each do |name|
  name = name.delete('-').split
  first_name = name[0]
  second_name = name[1]
  number_for_login = logins.count { |element| \
    login.include? element.delete '0-9' }.to_s
  flag1 = flag2 = false
  login = first_name[0] + second_name[0..6]
  logins.any? do |element|
    flag1 = true if login == element
    flag2 = true if login.chomp == element
  end
  login.chomp! if flag1
  if flag2
      login = first_name[0] + \
      second_name[0..[second_name.size - 1, 6 - number_for_login.size].min] + \
      number_for_login
    end
  end
  logins << login
end

puts logins

__END__
Анастасия Иванова
Иван Петров
Игнатий Петров
Игорь Петров
Ирина Петрова
Надежда Павлова
Михаил Перов-Розенштейн
