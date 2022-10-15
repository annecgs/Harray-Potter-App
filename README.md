# App Harray Potter

![Badge](https://visitor-counter-badge.vercel.app/api/annecgs/Harray-Potter-App/visitor-counter-badge/?label=Visitor&color=363636&labelColor=4682B4)
![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)
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


<h2>Demonstrativo tela de erro</h2>
<p float="left">
<img src="https://user-images.githubusercontent.com/103140224/178013725-4d78bc4c-bb2a-4535-b562-e86c5d275404.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/178013720-8d1db6d4-e81d-45c1-a1ad-d6a7f3db76bc.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/178013715-b29293f1-cb1d-4a05-a9bb-596770a0d6e4.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/178031727-8e578263-9764-4d85-a03c-71164fb43d35.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/178031771-05b384c0-7145-4447-9fb5-d5beeea73df8.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/178046064-653206e1-4e2e-47cb-a534-bfa542c8577c.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/178031702-50d1faaf-0ddc-4008-bada-9c622cf8a0ab.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/178034320-2389fb60-cc05-409e-98d2-8f642317071d.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/178031746-8d1fc3d4-9513-4570-b7f6-a31a55f6d0d5.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/178013707-beaf4fc9-276e-4c23-863c-146f56e1cf2f.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/178042718-0f7b4995-e1fd-4b2f-865e-347cb3ff839c.png" width="220px" height="350px"/>

</p>

<h2>Direitos autorais da imagens utilizadas no app</h2>
<a href="https://br.pinterest.com/pin/8585055527636654/"><li>Imagem utilizada no Splash Screen: Maraisa Souza (Pinterest)</li></a>
<a href="https://br.pinterest.com/pin/6755468181965453/"><li>Icons utilizados nas casas (grifinória, lufa lufa, corvinal e sonserina): deviantart.com</li></a>
<a href="https://www.pinclipart.com/pindetail/iJThxR_clipart-document-not-found-icon-png-download/"><li>Icon not found dados ficha cadastral: Clipart.com</li></a>
<a href="https://storyset.com/illustration/400-error-bad-request/rafiki"><li>Error 400: storyset.com</li></a>
<a href="https://storyset.com/illustration/401-error-unauthorized/pana"><li> Error 401: storyset.com</li></a>
<a href="https://storyset.com/illustration/403-error-forbidden/rafiki"><li> Error 403: storyset.com</li></a>
<a href="https://storyset.com/illustration/404-error-with-a-cute-animal/cuate"><li>Error 404: storyset.com</li></a>
<a href="https://images.fineartamerica.com/images/artworkimages/mediumlarge/2/silly-astronaut-cat-404-error-delf-design.jpg"><li>Error 410: images.fineartamerica.com (imagem adaptada para o error 410</li></a>
<a href="https://storyset.com/illustration/error-429/amico"><li>Error 429: storyset.com</li></a>
<a href="https://storyset.com/illustration/500-internal-server-error/cuate#FF81AEFF&hide=&hide=complete"><li>Error 500: storyset.com</li></a>
<a href="https://mauwebsite.vn/wp-content/uploads/2021/11/502-error.png"><li>Error 502: storyset.com</li></a>
<a href="https://storyset.com/illustration/503-error-service-unavailable/cuate#BA68C8FF&hide=&hide=complete"><li>Error 503: storyset.com</li></a>
<a href="https://storyset.com/illustration/504-error-gateway-timeout/rafiki"><li>Error 504: storyset.com</li></a>
<a href="https://pin.it/130DXYd"><li>Error 505: Freepik (a imagem foi adaptada para o error 505)</li></a>

<h2>Demonstrativo de tela para membro não localizado e lista de favoritos vazia</h2>
<p float="left">
<img src="https://user-images.githubusercontent.com/103140224/180868554-ab6958b0-66ce-48f8-af87-cd0408020469.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/180868567-19439216-5955-40bf-abcb-f2517c9307d8.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/180868569-97d8f2b1-a8a4-4bc8-9908-7633542f55cd.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/180860733-77eb1374-a554-4bbf-bce9-4d3c1fc5637b.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/180860746-c0d256a5-5fa9-4d56-947e-3139584e680d.png" width="220px" height="350px"/>
<img src="https://user-images.githubusercontent.com/103140224/180860752-487aeb57-e958-45c7-b0ab-0ac1e1c808ba.png" width="220px" height="350px"/>
</p>



