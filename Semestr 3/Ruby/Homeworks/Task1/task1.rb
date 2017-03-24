# encoding: UTF-8

lines = []

DATA.each_line.map(&:chomp).each { |line| lines << line}
puts "Start strings \n" 
puts lines

lines.each do |line|
	groups = line.to_s.split(/[\d\*\+\-]/).select{ |e| e.size() > 0 }
	if groups.values_at(0..2).count{|elem| elem.include? "f"} < 2
		groups.map!{ |e| e.include?("f") ? "tree" : e} 
	end
	puts groups
end

__END__
putfty12rufby1kfek123
sam01tif12lalka
kyky+++sir0lalfrf1231lfaa