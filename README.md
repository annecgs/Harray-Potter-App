# App Harray Potter

![Badge](https://visitor-counter-badge.vercel.app/api/annecgs/Harray-Potter-App/visitor-counter-badge/?label=Visitor&color=363636&labelColor=4682B4)
<p>Projeto pessoal desenvolvido para fins de estudo de mobile com aplicação da linguagem kotlin</p>

<h2>Informações do projeto</h2>
<p>O projeto consiste em desenvolver um aplicativo que seja capaz de listar todos os personagens do filme Harry Potter, mostrar detalhes de um personagem específico, classificar personagens como favoritos, remover a classificação de favoritos, consultar uma lista de personagens favoritos, consultar um alista de personagens de acordo com a casa a qual pertencem (grifinória, lufa lufa, corvinal ou sonserina)</p>

<h2>Tecnolgias e recursos utilizados para o desenvolvimento</h2>
<li>Retrofit para realizar a requisição da API</li>
<li>Moshi para atividades de serialização de objetos</li>
<li>Glide para carregar as imagens</li>
<li>Aplicação de fragments para reaproveitamento dos objetos visuais</li>
<li>Tratativa dos erros de API</li>
<li>Arquitetura MVVM</li>
<li>Boas práticas com aplicação do Ktlint</li>
<li>Acessibilidade, utilizar https://developer.android.com/guide/topics/ui/accessibility/principles para
referência ou https://guia-wcag.com/ ;
<li>Suporte versão do Android 12;</li>
<li>Modularização (a fazer!);</li>
<li>Testes unitários em JUnit (a fazer!);</li>

<h2>As características funcionais e não funcionais do app consistem em:</h2>
<li>Lista de personagens com scrolling infinito;</li>
<li>Adicionar um personagens aos favoritos;</li>
<li>Controle de estado (se um personagem foi adicionado aos favoritos ou não);</li>
<li>Indicar como personagem favorita logo na tela de listagem dos personagens;</li>
<li>Aplicar o conceito de modularização no aplicativo, importando/incluindo um projeto externo como uma library no Gradle.(a fazer!)</li>
<li> Deve existir uma tela padrão para erro com a opção de “tentar novamente”;</li>
<li>Para favoritos deverá ser utilizado algum meio de persistência local como por exemplo, Shared
preferences (não salvando toda resposta da api, apenas chave valor para marcar um personagem como
favorito)</li>


<h2>Fluxo principal - Listagem dos personagens do filme Harray Potter</h2>
<p float="left">
<img src="https://user-images.githubusercontent.com/103140224/177156105-42d870a2-ad32-45e3-862d-ebac3862e9ff.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/177796681-79c63daf-64ff-4e38-a263-0f226f504c5f.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/177796964-db9c52d8-8d6f-4f1c-ac27-e208aba42f76.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/177797000-f8c0c0ef-f7b3-4784-845f-3e4fd77f1bc8.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/178005055-fb888d52-558c-4cb5-98f1-7e96e5307b62.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/178005064-344fd186-fa16-47e9-86c4-255b0bb9b71e.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/178005073-f0f6e3f2-56e9-4aca-a05b-ff138e1cecaa.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/178005078-e6d77685-f32d-4fb8-9f3b-e02696dcf873.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/178005080-69470ebc-df7e-4cdd-85c7-e03918594654.png" width="220px" height="350px"/>
</p>

<h2>Aplicação da acessibilidade</h2>
<li>Adição de etiquetas com contentDescription</li>
<li>Elementos editáveis aplicação android:hint e android:labelFor</li>
<li>Grupos de conteúdo relacionado aplicação do android:screenReaderFocusable do objeto de contêiner como true e o atributo android:focusable de cada objeto interno como false.</li>


<h2>Aplicação de testes ViewModel</h2>
<li>kotlinx-coroutines-test</li>
<li>mockk</li>
<li>core:core-testing</li>

<h2>Direitos autorais da imagens utilizadas no app</h2>
<a href="https://br.pinterest.com/pin/8585055527636654/"><li>Imagem utilizada no Splash Screen: Maraisa Souza (Pinterest)</li></a>
<a href="https://br.pinterest.com/pin/6755468181965453/"><li>Icons utilizados nas casas (grifinória, lufa lufa, corvinal e sonserina): deviantart.com</li></a>
<a href="https://www.pinclipart.com/pindetail/iJThxR_clipart-document-not-found-icon-png-download/"><li>Icon not found dados ficha cadastral: Clipart.com</li></a>
