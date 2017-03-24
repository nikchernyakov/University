MAX_HEIGHT = 20

print 'Enter pyramid\'s height:'
while height = gets.chomp
  if height =~ /^-*\d+$/
    height = height.to_i
    if height < 0 
      is_negative = true
      height *= -1
    end
    if height > 0 && height <= MAX_HEIGHT
    	pyramid = []
      height.times do |step|
        width = ''.center 2 * (height - step) - 1, '*'
        pyramid.push width.center width.size + 2 * step
      end
      pyramid.reverse! if is_negative
      puts pyramid
      break
    else
      puts 'The value doesn\'t fall within the range! Enter pyramid\'s height:'
    end
  else
    puts 'Not a number! Enter pyramid\'s height:'
  end
end
