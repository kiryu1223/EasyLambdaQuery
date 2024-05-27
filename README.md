
### 开启方法
往maven的build的compilerArgs参数里面加入 -Xplugin:ExpressionTree
```maven
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>{你的maven版本}</version>
                <configuration>
                   <compilerArgs>
                      <arg>-Xplugin:ExpressionTree</arg>
                   </compilerArgs>
                </configuration>
            </plugin>
        </plugins>
    </build>
```