==== here-document-method
def z
print <<EOS1
Hello world
EOS1
end
==
def z
	print <<EOS1
Hello world
EOS1
end
==== here-document-method-minus
def z
print <<-EOS2
Hello world
EOS2
end
==
def z
	print <<-EOS2
Hello world
	EOS2
end
==== here-document-if-block
a = 1
if a == 1
print <<EOS3
aaaa
bbbb
EOS3
end
==
a = 1
if a == 1
	print <<EOS3
aaaa
bbbb
EOS3
end
==== here-document-if-block-minus
a = 1
if a == 1
print <<-EOS4
aaaa
bbbb
EOS4
end
==
a = 1
if a == 1
	print <<-EOS4
aaaa
bbbb
	EOS4
end
==== here-document-unless-modifier
begin
print <<EOS5
hello
world
EOS5
end unless i < 2
==
begin
	print <<EOS5
hello
world
EOS5
end unless i < 2
==== here-document-unless-modifier-minus
begin
print <<-EOS6
hello
world
EOS6
end unless i < 2
==
begin
	print <<-EOS6
hello
world
	EOS6
end unless i < 2
==== here-document-do-block
[ 1, 2, 3 ].each do |x|
print <<EOS7
aaa
bbb
EOS7
end
==
[ 1, 2, 3 ].each do |x|
	print <<EOS7
aaa
bbb
EOS7
end
==== here-document-do-block-minus
[ 1, 2, 3 ].each do |x|
print <<-EOS8
aaa
bbb
EOS8
end
==
[ 1, 2, 3 ].each do |x|
	print <<-EOS8
aaa
bbb
	EOS8
end